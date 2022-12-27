package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.AssessParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Parameter;
import java.util.List;

@Mapper
@Repository
public interface AssessParameterMapper extends BaseMapper<AssessParameter> {
    @Select("select * from assess_parameter where scheme_id=#{schemeId} And is_delete=0")
    List<AssessParameter> selecetParameterBySchemeId(Long schemeId);

}
