package com.javaTraining.nobug.controller;

import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.AssessParameterMapper;
import com.javaTraining.nobug.mapper.ThresholdMapper;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.service.ThresholdService;
import com.javaTraining.nobug.vo.ThresholdVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;

@RestController
@RequestMapping("/threshold")
@Api(value = "阈值接口",tags = "阈值相关的接口")
@Slf4j
public class ThresholdController {
    @Autowired
    private ThresholdService thresholdService;

    @Autowired
    private ThresholdMapper thresholdMapper;

    @Autowired
    private AssessParameterMapper assessParameterMapper;
    @GetMapping("/getThresholdInfoById")
    public OutputObject SelectThresholdById(@RequestParam(required = true) Long thresholdId,@RequestHeader(name = "token") String token){
        ThresholdVo thresholdVo = thresholdMapper.SelectThresholdById(thresholdId);
        AssessParameter name = assessParameterMapper.selectById(thresholdVo.getParameterId());
        thresholdVo.setParameterName(name.getParameterName());
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",thresholdVo);
    }
}
