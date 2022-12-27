package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.AssessParameterMapper;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.service.AssessParameterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AssessParamterServiceImpl extends ServiceImpl<AssessParameterMapper, AssessParameter> implements AssessParameterService {

    @Resource
    private AssessParameterMapper assessParameterMapper;
}
