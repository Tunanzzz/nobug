package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.pojo.AssessmentScheme;
import com.javaTraining.nobug.vo.AssessSchemeVo;

import java.util.List;
import java.util.Map;

public interface AssessmentSchemeService extends IService<AssessmentScheme> {

    AssessSchemeVo getSchemeById(Long schemeId);

    List<AssessSchemeVo> getSchemeByProjectClassId(Long projectClassId);
}
