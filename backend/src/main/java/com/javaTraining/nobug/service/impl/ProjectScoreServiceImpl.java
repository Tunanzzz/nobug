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
            Double defectRatio = 10.0;      // 缺陷率
            Double costTimeRatio = 0.0;     // 总人月比例
            Double onTimeRatio = 1.0;  //及时率
            Project projectInfo = projectMapper.selectById(projectId);
            Map<String,ArrayList<ThresholdVo>> thresholdMap = new HashMap<>();
            /* ------------ 将阈值按照阈值类型分类 --------------*/
            for(AssessParameter assessParameter : assessSchemeVo.getAssessParameterList()){
                ArrayList<ThresholdVo> ls = new ArrayList<ThresholdVo>();
                thresholdMap.put(assessParameter.getParameterName(), ls);
            }
            for(ThresholdVo thresholdVo : assessSchemeVo.getThresholdVoList()){
                ArrayList<ThresholdVo> list = thresholdMap.get(thresholdVo.getParameterName());
                list.add(thresholdVo);
                thresholdMap.replace(thresholdVo.getParameterName(), list);
            }
            /* ------------------- 计算各参数 -------------------------*/
            Double taskCount = Double.valueOf(taskResponseVoList.size());  // 任务总数
            Double defectCount = Double.valueOf(defectResponseVoList.size());  // 缺陷总数
            Double planTimeTotal = 0.0;     // 计划总人月
            Double actualTimeTotal = 0.0;   // 实际总人月
            for(TaskResponseVo taskResponseVo : taskResponseVoList){
                planTimeTotal += taskResponseVo.getPlanTime();      // 计算计划总人月
                actualTimeTotal += taskResponseVo.getCostTime();    // 计算实际总人月
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
            Double actualFinishTime = (Double)((actualEndTime- actualStartTime) / msToHour);   // 实际完成项目的耗时
            Double scheduledFinishTime = (Double)((scheduledEndTime - scheduledStartTime) / msToHour);  // 计划耗时
            onTimeRatio = actualFinishTime / scheduledFinishTime;
            /* --------------------- 计算得分 -------------------*/
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
        Double lowValue = null; // 小阈值
        Double highValue = null;   // 小阈值比重
        Double lowWeight = null;    // 大阈值
        Double highWeight = null;   // 大阈值比重
        Double minWeight = thresholdVoList.get(0).getParameterScore();
        Double maxWeight = thresholdVoList.get(thresholdVoList.size()-1).getParameterScore();
//        System.out.println(thresholdVoList);    // 按照阈值升序排序
        Boolean hasHigh = false;    // 只要找到大阈值，大阈值就不变了
        for(ThresholdVo thresholdVo : thresholdVoList){     // 找大阈值和小阈值
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
            return 100 * minWeight * paraWeight;     // 比最小的阈值小
        }else if (highValue == null){
            return 100* maxWeight * paraWeight;      // 比最大的阈值大
        }else if(lowValue == highValue){
            return  100 * lowWeight * paraWeight;    // 最大阈值和最小阈值相等
        }else{
            // 存在最大和最小阈值,且不相等
            Double k = (highWeight - lowWeight) / (highValue - lowValue);  // 斜率
            return 100 * (lowWeight + k * (ratio - lowValue)) * paraWeight;  // 通过斜率求分数
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
