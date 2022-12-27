package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.pojo.Threshold;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessSchemeVo {
    private Long schemeId;
    private String schemeName;
    private Double schemeVersion;
    private Date createTime;
    private Long projectClassId;
    private Integer importantDegree;
    private Integer isDisable;      //0 未禁用,1 禁用
    private Integer isDelete;
    private List<AssessParameter> assessParameterList;
    private List<ThresholdVo> thresholdVoList;
}
