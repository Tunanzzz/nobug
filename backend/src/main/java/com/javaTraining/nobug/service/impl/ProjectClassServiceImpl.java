package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.ProjectClassMapper;
import com.javaTraining.nobug.pojo.ProjectClass;
import com.javaTraining.nobug.service.ProjectClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectClassServiceImpl extends ServiceImpl<ProjectClassMapper, ProjectClass> implements ProjectClassService {

    @Resource
    private ProjectClassMapper projectClassMapper;
}
