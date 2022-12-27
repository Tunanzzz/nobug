package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.mapper.ProjectMapper;
import com.javaTraining.nobug.mapper.ProjectScoreMapper;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.ProjectScore;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.ProjectScoreService;
import com.javaTraining.nobug.vo.ProjectScoreRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projectScore")
@Api(value = "项目得分接口",tags = "项目得分相关的接口")
@Slf4j
public class ProjectScoreController {

    @Autowired
    private ProjectScoreService projectScoreService;
    @Autowired
    private ProjectScoreMapper projectScoreMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @GetMapping("/calculateProjectScoreBySchemeId")
    public OutputObject calculateProjectScoreBySchemeId(@RequestParam(required = true) Long projectId,@RequestParam(required = true) Long schemeId,
                                                        @RequestParam(required = true) Long review_user_id, @RequestHeader String token) {
        Map<String,Object> resultMap = projectScoreService.calculateProjectScoreBySchemeId(projectId,schemeId,review_user_id);
        if (resultMap == null){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"项目不存在或已被删除",null);
        }else {
            return new OutputObject(String.valueOf(HttpStatus.OK.value()),"计算指标得分成功",resultMap) ;
        }
    }

    @PostMapping("/commitReviewProject")
    public OutputObject commitReviewProject(@RequestBody ProjectScoreRequestVo pv, @RequestHeader String token){
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",pv.getProjectId());
        map.put("reviewUserId",pv.getReviewUserId());
        map.put("message",pv.getMessage());
        map.put("additionScore",pv.getAdditionScore());
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id",pv.getProjectId());
        Project project =  projectMapper.selectOne(queryWrapper);
        if(project == null){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"项目不存在",null);
        }else if( project.getStatus() > 3){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"项目已被审核，无法再次审核",null);
        }else {
            Boolean b = projectScoreMapper.commitReviewProject(map);
            project.setStatus(3);
            projectMapper.update(project,queryWrapper);
            if(b = false){
                return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"审核失败",null);
            }else {
                return new OutputObject(String.valueOf(HttpStatus.OK.value()),"审核成功",null) ;
            }
        }

    }

    @GetMapping("/getProjectRankInSameAssessScheme")
    public OutputObject getProjectRankInSameAssessScheme(@RequestHeader String token, Long projectId){

        double rankPercent = projectScoreService.getProjectRankInSameAssessScheme(projectId);

        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",rankPercent);
    }
}
