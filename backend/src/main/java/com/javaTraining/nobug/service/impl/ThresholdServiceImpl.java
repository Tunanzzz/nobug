package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.ThresholdMapper;
import com.javaTraining.nobug.pojo.Threshold;
import com.javaTraining.nobug.service.ThresholdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThresholdServiceImpl extends ServiceImpl<ThresholdMapper, Threshold> implements ThresholdService {

    @Resource
    private ThresholdMapper thresholdMapper;
}
