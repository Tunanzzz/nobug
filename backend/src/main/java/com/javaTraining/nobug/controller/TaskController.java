package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.ProjectMapper;
import com.javaTraining.nobug.mapper.TaskMapper;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.Task;
import com.javaTraining.nobug.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
@Api(value = "任务接口",tags = "任务相关的接口")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/selectTaskByProjectId")
    @ResponseBody
    public OutputObject selectTaskByProjectId(@RequestParam(required = true) Long projectId, @RequestHeader String token){
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id",projectId);
        if(projectMapper.selectOne(queryWrapper).getIsDelete() == 1){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"该项目不存在或已被删除",null);
        }
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",taskMapper.selectTaskByProjectId(projectId));
    }

    @PostMapping(value = "/selectTaskByConditions",produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据筛选条件筛选出任务",tags = "返回满足条件的任务基本信息")
    public OutputObject selectTaskByUserId(@RequestBody Map<String,Object> reqMap, @RequestHeader String token){
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if (reqMap.get("userId")!=null) queryWrapper.in("user_id",reqMap.get("userId"));
        if (reqMap.get("status")!=null) queryWrapper.in("status",reqMap.get("status"));
        if (reqMap.get("importantDegree")!=null) queryWrapper.in("important_degree",reqMap.get("importantDegree"));
        if (reqMap.get("taskMinReleaseTime")!=null) queryWrapper.ge("task_release_time",reqMap.get("taskMinReleaseTime"));
        if (reqMap.get("taskMaxReleaseTime")!=null) queryWrapper.le("task_release_time",reqMap.get("taskMaxReleaseTime"));
        queryWrapper.in("is_delete",0);
        List<Task> taskList = taskMapper.selectList(queryWrapper);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",taskList);
    }
}
