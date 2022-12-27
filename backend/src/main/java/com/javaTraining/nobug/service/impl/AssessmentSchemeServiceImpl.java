package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.AssessParameterMapper;
import com.javaTraining.nobug.mapper.AssessmentSchemeMapper;
import com.javaTraining.nobug.mapper.ThresholdMapper;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.pojo.AssessmentScheme;
import com.javaTraining.nobug.service.AssessmentSchemeService;
import com.javaTraining.nobug.vo.AssessSchemeVo;
import com.javaTraining.nobug.vo.ThresholdVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AssessmentSchemeServiceImpl extends ServiceImpl<AssessmentSchemeMapper, AssessmentScheme> implements AssessmentSchemeService {

    @Resource
    private AssessmentSchemeMapper assessmentSchemeMapper;
    @Resource
    private AssessParameterMapper assessParameterMapper;
    @Resource
    private ThresholdMapper thresholdMapper;
    @Resource
    private AssessmentSchemeService assessmentSchemeService;

    @Override
    public AssessSchemeVo getSchemeById(Long schemeId) {
        QueryWrapper<AssessmentScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scheme_id",schemeId);
        // 如果方案已经被删除
        if (assessmentSchemeMapper.selectOne(queryWrapper).getIsDelete() == 1 || assessmentSchemeMapper.selectOne(queryWrapper) == null){
            return null;
        }
        AssessSchemeVo assessSchemeVo = assessmentSchemeMapper.getSchemeById(schemeId);
        assessSchemeVo.setAssessParameterList(assessParameterMapper.selecetParameterBySchemeId(assessSchemeVo.getSchemeId()));
        assessSchemeVo.setThresholdVoList(new ArrayList<ThresholdVo>());
        ArrayList<ThresholdVo> list = new ArrayList<>();
        for(AssessParameter parameter : assessSchemeVo.getAssessParameterList()) {
            list.addAll(thresholdMapper.SelectThresholdByParameterId(parameter.getParameterId()));
        }
        assessSchemeVo.setThresholdVoList(list);
        return assessSchemeVo;
    }

    @Override
    public List<AssessSchemeVo> getSchemeByProjectClassId(Long projectClassId) {
        QueryWrapper<AssessmentScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_class_id",projectClassId);
        queryWrapper.in("is_delete",0);
        queryWrapper.in("is_disable",0);
        List<AssessmentScheme> assessSchemes = assessmentSchemeMapper.selectList(queryWrapper);
        ArrayList<AssessSchemeVo> assessSchemeVoArrayList = new ArrayList<>();
        for(AssessmentScheme assessmentScheme : assessSchemes){
            assessSchemeVoArrayList.add(assessmentSchemeService.getSchemeById(assessmentScheme.getSchemeId()));
        }
        return assessSchemeVoArrayList;
    }
}
