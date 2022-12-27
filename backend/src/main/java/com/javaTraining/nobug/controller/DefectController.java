package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.DefectMapper;
import com.javaTraining.nobug.mapper.ProjectMapper;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.service.DefectService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defect")
@Api(value = "缺陷接口",tags = "缺陷相关的接口")
@Slf4j
public class DefectController {
    @Autowired
    private DefectService defectService;
    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/selectDefectByProjectId")
    @ResponseBody
    public OutputObject selectDefectyProjectId(@RequestParam(required = true) Long projectId, @RequestHeader String token){
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id",projectId);
        if(projectMapper.selectOne(queryWrapper).getIsDelete() == 1){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"该项目不存在或已被删除",null);
        }
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",defectMapper.selectDefectByProjectId(projectId));
    }
}
