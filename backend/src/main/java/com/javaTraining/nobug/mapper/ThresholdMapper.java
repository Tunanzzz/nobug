package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.Threshold;
import com.javaTraining.nobug.vo.ThresholdVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ThresholdMapper extends BaseMapper<Threshold> {

    ThresholdVo SelectThresholdById(Long thresholdId);

    List<ThresholdVo> SelectThresholdByParameterId(Long parameterId);
}
