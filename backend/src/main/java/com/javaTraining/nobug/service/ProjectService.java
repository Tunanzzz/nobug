package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.vo.ProjectVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ProjectService extends IService<Project> {

    OutputObject getProjectListByPage(Map<String,Object> map);

    OutputObject getProjectListbyLimit(Map<String,Object> map);

    Map<String,Object> getProjectDetailById(Long projectId);

    ResultObj commitProject(Long projectId);

    ProjectVo getProjectById(Long projectId);

    List<User> getProjectMasterUsers();

    Project selectOne(QueryWrapper<Project> queryWrapper);
}
