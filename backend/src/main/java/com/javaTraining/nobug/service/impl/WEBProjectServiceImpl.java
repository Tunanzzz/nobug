package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.mapper.DefectMapper;
import com.javaTraining.nobug.mapper.ProjectMapper;
import com.javaTraining.nobug.mapper.ProjectScoreMapper;
import com.javaTraining.nobug.mapper.TaskMapper;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.ProjectService;
import com.javaTraining.nobug.service.factory.core.ProjectEnum;
import com.javaTraining.nobug.vo.ProjectVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WEBProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DefectMapper defectMapper;
    @Override
    public ProjectVo getProjectById(Long projectId) {
        return projectMapper.getProjectById(projectId);
    }
    @Override
    public OutputObject getProjectListByPage(Map<String, Object> map) {
        List<ProjectVo> projectVoList = projectMapper.getProjectListByPage(map);
        Integer count = projectVoList.size();
        HashMap<String, Object> hs = new HashMap<>();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        List<ProjectVo> projectVos;
        if (pageNum * pageSize <= count) {
            projectVos = projectVoList.subList(pageNum * pageSize - pageSize, pageNum * pageSize);
        } else {
            projectVos = projectVoList.subList(pageNum * pageSize - pageSize, count);
        }
        hs.put("projectList", projectVos);
        hs.put("totalCount", count);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "??????", hs);
    }
    public OutputObject getProjectListbyLimit(Map<String, Object> map) {
        List<Long> projectIdList = projectMapper.getProjectListByLimit(map);
        List<Map<String, Object>> projectVoList = Lists.newArrayList();
        for (Long projectId : projectIdList) {
            projectVoList.add(getProjectDetailById(projectId));
        }
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "??????", projectVoList);
    }
    @Override
    public Map<String, Object> getProjectDetailById(Long projectId) {
        Map<String, Object> resultmap = new HashMap<>();
        ProjectVo projectVo = projectMapper.getProjectById(projectId);
        if (projectVo == null) {
            return null;
        }
        if (projectVo.getStatus() > 2) {
            projectVo.setScore(projectVo.getAdditionScore() + projectVo.getDefectScore()
                    + projectVo.getDefectScore() + projectVo.getOnTimeScore());
        }

        resultmap.put("projectBaseInfo", projectVo);
        resultmap.put("tasks", taskMapper.selectTaskByProjectId(projectId));
        resultmap.put("defects", defectMapper.selectDefectByProjectId(projectId));
        return resultmap;
    }
    public ResultObj commitProject(Long projectId) {
        try {
            QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<Project>();
            projectQueryWrapper.in("project_id", projectId);
            Project project = projectMapper.selectOne(projectQueryWrapper);
            if (project.getStatus() == 3) {
                UpdateWrapper<Project> projectUpdateWrapper = new UpdateWrapper<Project>();
                projectUpdateWrapper.eq("project_id", projectId);
                projectUpdateWrapper.set("status", 4);
                projectMapper.update(null, projectUpdateWrapper);
                return ResultObj.UPDATE_SUCCESS;
            } else {
                return ResultObj.UPDATE_FAIL;
            }
        } catch (Exception e) {
            return ResultObj.UPDATE_FAIL;
        }
    }
    @Override
    public List<User> getProjectMasterUsers() {
        return projectMapper.getProjectMasterUsers();
    }

    @Override
    public Project selectOne(QueryWrapper<Project> queryWrapper) {
        return projectMapper.selectOne(queryWrapper);
    }

    @Override
    public ProjectEnum supportType() {
        return ProjectEnum.WEB_PROJECT;
    }
}
