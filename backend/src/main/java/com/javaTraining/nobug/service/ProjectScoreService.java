package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.pojo.ProjectScore;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.vo.ThresholdVo;

import java.util.List;
import java.util.Map;

public interface ProjectScoreService extends IService<ProjectScore> {

    public Map<String,Object> calculateProjectScoreBySchemeId(Long projectId,Long schemeId,Long reviewUserId);

    public Double calculateScoreByThreshold(Double ratio, List<ThresholdVo> thresholdVoList,Double paraWeight);

    public Double getProjectRankInSameAssessScheme(Long projectId);
}
