package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.pojo.ProjectClass;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.service.ProjectClassService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectClass")
@Api(value = "项目类别接口",tags = "项目类别相关的接口")
public class ProjectClassController {

    @Autowired
    private ProjectClassService projectClassService;

    @GetMapping("/getAllProjectClasses")
    public OutputObject getAllProjectClasses(@RequestHeader(name = "token") String token){
        QueryWrapper<ProjectClass> queryWrapper = new QueryWrapper<>();
        List<ProjectClass> projectClasses = projectClassService.list(queryWrapper);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",projectClasses);
    }
}
