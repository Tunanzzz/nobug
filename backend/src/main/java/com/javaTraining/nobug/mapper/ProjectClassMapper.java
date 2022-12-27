package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.ProjectClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectClassMapper extends BaseMapper<ProjectClass> {
}
