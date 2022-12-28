package com.javaTraining.nobug.controller;

import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.AssessmentSchemeService;
import com.javaTraining.nobug.service.DefectService;
import com.javaTraining.nobug.service.ProjectService;
import com.javaTraining.nobug.service.TaskService;
import com.javaTraining.nobug.vo.AssessSchemeVo;
import com.javaTraining.nobug.vo.ProjectScoreRequestVo;
import com.javaTraining.nobug.vo.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
@Api(value = "项目接口", tags = "项目相关的接口")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    //    @Autowired
//    private ProjectMapper projectMapper;
    @Autowired
    private TaskService taskService;
//    @Autowired
//    private TaskMapper taskMapper;
    @Autowired
    private DefectService defectService;
//    @Autowired
//    private DefectMapper defectMapper;
    @Autowired
    private AssessmentSchemeService assessmentSchemeService;

    @GetMapping("/getProjectListByPage")
    @ApiOperation(value = "根据条件筛选获取项目列表", notes = "返回已排好序的表格字段需求信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "projectName", value = "项目名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "projectClassId", value = "项目类型ID", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "项目负责人ID", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "scheduledStartTime", value = "项目计划开始时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "scheduledEndTime", value = "项目计划结束时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "actualStartTime", value = "项目实际开始时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "actualEndTime", value = "项目实际结束时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "minScore", value = "最低分", paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "maxScore", value = "最高分", paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "importantDegree", paramType = "query", dataType = "int")
    })
    public OutputObject getProjectListByPage(@ApiIgnore @RequestParam Map<String, Object> map, @RequestHeader String token) {
        Object pageNum = map.get("pageNum");
        Object pageSize = map.get("pageSize");
        if (pageNum != null && pageSize != null) {
            map.replace("pageNum", Integer.parseInt(pageNum.toString()));
            map.replace("pageSize", Integer.parseInt(pageSize.toString()));
        }
        return projectService.getProjectListByPage(map);
    }

    @GetMapping("/getProjectDetailById")
    public OutputObject getProjectDetailById(@RequestParam Long projectId, @RequestHeader String token) {
        Map<String, Object> resultmap = new HashMap<>();
        ProjectVo projectVo = projectService.getProjectById(projectId);
//        System.out.println(projectVo);
        if (projectVo == null) {
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()), "项目不存在或已删除", null);
        }
        if (projectVo.getStatus() > 2) {
            projectVo.setScore(projectVo.getAdditionScore() + projectVo.getDefectScore()
                    + projectVo.getDefectScore() + projectVo.getOnTimeScore());
        }
        AssessSchemeVo assessSchemeVo = assessmentSchemeService.getSchemeById(projectVo.getSchemeId());
        resultmap.put("schemeInfo", assessSchemeVo);
        resultmap.put("projectBaseInfo", projectVo);
        resultmap.put("tasks", taskService.selectTaskByProjectId(projectId));
        resultmap.put("defects", defectService.selectDefectByProjectId(projectId));

        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", resultmap);
    }

    @GetMapping("/getProjectListByLimit")
    @ApiOperation(value = "根据条件筛选获取项目详情列表", notes = "返回已排好序的表格字段需求信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "projectClassId", value = "项目类型ID", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "项目负责人ID", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "scheduledStartTime", value = "项目计划开始时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "scheduledEndTime", value = "项目计划结束时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "actualStartTime", value = "项目实际开始时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "actualEndTime", value = "项目实际结束时间", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "minScore", value = "最低分", paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "maxScore", value = "最高分", paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "importantDegree", paramType = "query", dataType = "int")
    })
    public OutputObject getProjectListByLimit(@ApiIgnore @RequestParam Map<String, Object> map, @RequestHeader String token) {

        return projectService.getProjectListbyLimit(map);
    }

    @GetMapping("/getProjectMasterUsers")
    public OutputObject getProjectMasterUsers(@RequestHeader String token) {
        Map<String, Object> resultmap = new HashMap<>();
        List<User> users = projectService.getProjectMasterUsers();
        resultmap.put("users", users);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", users);
    }

    @PostMapping("/commitProject")
    @ApiOperation(value = "确认项目")
    public ResultObj commitProject(@RequestHeader String token, @RequestBody ProjectScoreRequestVo projectScoreRequestVo) {
        System.out.println(projectScoreRequestVo);
        return projectService.commitProject(projectScoreRequestVo.getProjectId());
    }
}
