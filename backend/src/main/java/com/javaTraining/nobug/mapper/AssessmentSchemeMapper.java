package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.AssessmentScheme;
import com.javaTraining.nobug.vo.AssessSchemeVo;
import com.javaTraining.nobug.vo.UserResponseVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AssessmentSchemeMapper extends BaseMapper<AssessmentScheme> {

    AssessSchemeVo getSchemeById(Long schemeId);

    public List<UserResponseVo> getSchemeListByPage(Map<String,Object> map);
}
