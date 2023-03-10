package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.*;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.ProjectScore;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.AssessmentSchemeService;
import com.javaTraining.nobug.service.ProjectScoreService;
import com.javaTraining.nobug.vo.AssessSchemeVo;
import com.javaTraining.nobug.vo.DefectResponseVo;
import com.javaTraining.nobug.vo.TaskResponseVo;
import com.javaTraining.nobug.vo.ThresholdVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProjectScoreServiceImpl extends ServiceImpl<ProjectScoreMapper, ProjectScore> implements ProjectScoreService {

    @Resource
    private ProjectScoreMapper projectScoreMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private DefectMapper defectMapper;

    @Resource
    private AssessmentSchemeService assessmentSchemeService;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private AssessParameterMapper assessParameterMapper;

    private Double msToHour = Double.valueOf(1000*60*60);
    @Override
    public Map<String, Object> calculateProjectScoreBySchemeId(Long projectId,Long schemeId,Long reviewUserId) {
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id",projectId);
        queryWrapper.in("is_delete",0);
        QueryWrapper<ProjectScore> q = new QueryWrapper<>();
        q.in("project_id",projectId);
        Project project = projectMapper.selectOne(queryWrapper);
        if(project == null) return  null;
        if( project.getStatus() > 3 && projectScoreMapper.selectOne(q) != null){
            ProjectScore projectScore = projectScoreMapper.selectOne(q);
//            System.out.println(projectScore + "====");
            resultMap.put("projectId",projectId);
            resultMap.put("schemeId",schemeId);
            resultMap.put("defectScore",projectScore.getDefectScore());
            resultMap.put("costTimeScore",projectScore.getCostTimeScore());
            resultMap.put("onTimeScore",projectScore.getOnTimeScore());
            QueryWrapper<AssessParameter> parameterQueryWrapper = new QueryWrapper<>();
            parameterQueryWrapper.in("scheme_id",schemeId);
            parameterQueryWrapper.in("is_delete",0);
            List<AssessParameter> assessParameters = assessParameterMapper.selectList(parameterQueryWrapper);
            resultMap.put("parameterList",assessParameters);
            return resultMap;
        }else{
            List<TaskResponseVo> taskResponseVoList = taskMapper.selectTaskByProjectId(projectId);
            List<DefectResponseVo> defectResponseVoList = defectMapper.selectDefectByProjectId(projectId);
            AssessSchemeVo assessSchemeVo = assessmentSchemeService.getSchemeById(schemeId);
            Double defectRatio = 10.0;      // ?????????
            Double costTimeRatio = 0.0;     // ???????????????
            Double onTimeRatio = 1.0;  //?????????
            Project projectInfo = projectMapper.selectById(projectId);
            Map<String,ArrayList<ThresholdVo>> thresholdMap = new HashMap<>();
            /* ------------ ????????????????????????????????? --------------*/
            for(AssessParameter assessParameter : assessSchemeVo.getAssessParameterList()){
                ArrayList<ThresholdVo> ls = new ArrayList<ThresholdVo>();
                thresholdMap.put(assessParameter.getParameterName(), ls);
            }
            for(ThresholdVo thresholdVo : assessSchemeVo.getThresholdVoList()){
                ArrayList<ThresholdVo> list = thresholdMap.get(thresholdVo.getParameterName());
                list.add(thresholdVo);
                thresholdMap.replace(thresholdVo.getParameterName(), list);
            }
            /* ------------------- ??????????????? -------------------------*/
            Double taskCount = Double.valueOf(taskResponseVoList.size());  // ????????????
            Double defectCount = Double.valueOf(defectResponseVoList.size());  // ????????????
            Double planTimeTotal = 0.0;     // ???????????????
            Double actualTimeTotal = 0.0;   // ???????????????
            for(TaskResponseVo taskResponseVo : taskResponseVoList){
                planTimeTotal += taskResponseVo.getPlanTime();      // ?????????????????????
                actualTimeTotal += taskResponseVo.getCostTime();    // ?????????????????????
            }
            if(taskCount != 0.0){
                defectRatio = Double.valueOf(defectCount / taskCount);
            }
            if(planTimeTotal != 0.0){
                costTimeRatio = actualTimeTotal / planTimeTotal;
            }
            Long actualEndTime = projectInfo.getActualEndTime().getTime();
            Long scheduledStartTime = projectInfo.getScheduledStartTime().getTime();
            Long scheduledEndTime = projectInfo.getScheduledEndTime().getTime();
            Long actualStartTime = projectInfo.getActualStartTime().getTime();
            Double actualFinishTime = (Double)((actualEndTime- actualStartTime) / msToHour);   // ???????????????????????????
            Double scheduledFinishTime = (Double)((scheduledEndTime - scheduledStartTime) / msToHour);  // ????????????
            onTimeRatio = actualFinishTime / scheduledFinishTime;
            /* --------------------- ???????????? -------------------*/
//            System.out.println((List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(2).getParameterName()));
            Double defectScore = calculateScoreByThreshold(defectRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(2).getParameterName()),assessSchemeVo.getAssessParameterList().get(2).getParameterWeight());
            Double costTimeScore = calculateScoreByThreshold(costTimeRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(1).getParameterName()),assessSchemeVo.getAssessParameterList().get(1).getParameterWeight());
            Double onTimeScore = calculateScoreByThreshold(onTimeRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(0).getParameterName()),assessSchemeVo.getAssessParameterList().get(0).getParameterWeight());
            Double defectScoreWithoutWeight = calculateScoreByThreshold(defectRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(2).getParameterName()),1.0);
            Double costTimeScoreWithoutWeight = calculateScoreByThreshold(costTimeRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(1).getParameterName()),1.0);
            Double onTimeScoreWithoutWeight = calculateScoreByThreshold(onTimeRatio, (List<ThresholdVo>) thresholdMap.get(assessSchemeVo.getAssessParameterList().get(0).getParameterName()),1.0);
            resultMap.put("projectId",projectId);
            resultMap.put("schemeId",schemeId);
            QueryWrapper<AssessParameter> parameterQueryWrapper = new QueryWrapper<>();
            parameterQueryWrapper.in("scheme_id",schemeId);
            parameterQueryWrapper.in("is_delete",0);
            List<AssessParameter> assessParameters = assessParameterMapper.selectList(parameterQueryWrapper);
            resultMap.put("parameterList",assessParameters);
            if(projectScoreMapper.selectOne(q) == null){
                projectScoreMapper.insert(new ProjectScore(projectId,onTimeScore,defectScore,costTimeScore,schemeId,reviewUserId));
            }else {
                projectScoreMapper.update(new ProjectScore(projectId,onTimeScore,defectScore,costTimeScore,schemeId,reviewUserId),q);
            }
            if(project.getStatus() <= 2 ){
                project.setStatus(2);
            }
            projectMapper.update(project,queryWrapper);
            resultMap.put("defectScore",defectScoreWithoutWeight);
            resultMap.put("costTimeScore",costTimeScoreWithoutWeight);
            resultMap.put("onTimeScore",onTimeScoreWithoutWeight);
            return resultMap;
        }
    }

    @Override
    public Double calculateScoreByThreshold(Double ratio, List<ThresholdVo> thresholdVoList,Double paraWeight) {
        Double lowValue = null; // ?????????
        Double highValue = null;   // ???????????????
        Double lowWeight = null;    // ?????????
        Double highWeight = null;   // ???????????????
        Double minWeight = thresholdVoList.get(0).getParameterScore();
        Double maxWeight = thresholdVoList.get(thresholdVoList.size()-1).getParameterScore();
//        System.out.println(thresholdVoList);    // ????????????????????????
        Boolean hasHigh = false;    // ?????????????????????????????????????????????
        for(ThresholdVo thresholdVo : thresholdVoList){     // ????????????????????????
            Double thresholdValue = thresholdVo.getParameterValue();
            Double thresholdWeight = thresholdVo.getParameterScore();
            if(thresholdValue <= ratio){
                lowValue = thresholdValue;
                lowWeight = thresholdWeight;
            }else if(thresholdValue >= ratio && hasHigh == false){
                highValue = thresholdValue;
                highWeight = thresholdWeight;
                hasHigh = true;
            }
        }
        if(lowValue == null) {
            return 100 * minWeight * paraWeight;     // ?????????????????????
        }else if (highValue == null){
            return 100* maxWeight * paraWeight;      // ?????????????????????
        }else if(lowValue == highValue){
            return  100 * lowWeight * paraWeight;    // ?????????????????????????????????
        }else{
            // ???????????????????????????,????????????
            Double k = (highWeight - lowWeight) / (highValue - lowValue);  // ??????
            return 100 * (lowWeight + k * (ratio - lowValue)) * paraWeight;  // ?????????????????????
        }
    }

    @Override
    public Double getProjectRankInSameAssessScheme(Long projectId){
        double rank = 0;
        QueryWrapper<ProjectScore> projectScoreQueryWrapper = new QueryWrapper<ProjectScore>();
        projectScoreQueryWrapper.eq("project_id",projectId);
        QueryWrapper<ProjectScore> ProjectScoreQuery = new QueryWrapper<ProjectScore>();
        ProjectScoreQuery.eq("scheme_id",projectScoreMapper.selectOne(projectScoreQueryWrapper).getSchemeId());
        ProjectScoreQuery.orderByAsc("on_time_score+defect_score+cost_time_score+addition_score");
        List<ProjectScore> Projects = projectScoreMapper.selectList(ProjectScoreQuery);
        double Count = Projects.size();
        int iCount = 0;
        for(int i = 0; i < Count; i++){
            if(Projects.get(i).getProjectId() == projectId){
                iCount = i;
            }
        }
        iCount += 1;
        rank = iCount/Count;
        return rank;
    }

    @Override
    public Boolean commitReviewProject(Map<String, Object> map) {
        return projectScoreMapper.commitReviewProject(map);
    }
}
