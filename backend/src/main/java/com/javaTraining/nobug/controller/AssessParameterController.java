package com.javaTraining.nobug.controller;

import com.javaTraining.nobug.service.AssessParameterService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessParameter")
@Api(value = "评估参数接口",tags = "评估参数相关的接口")
@Slf4j
public class AssessParameterController {

    @Autowired
    private AssessParameterService assessParameterService;
}
