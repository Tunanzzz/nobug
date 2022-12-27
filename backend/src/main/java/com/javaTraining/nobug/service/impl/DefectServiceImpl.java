package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.DefectMapper;
import com.javaTraining.nobug.pojo.Defect;
import com.javaTraining.nobug.service.DefectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefectServiceImpl extends ServiceImpl<DefectMapper, Defect> implements DefectService {

    @Resource
    private DefectMapper defectMapper;
}
