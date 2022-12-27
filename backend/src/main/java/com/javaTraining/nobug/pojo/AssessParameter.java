package com.javaTraining.nobug.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("assess_parameter")
public class AssessParameter {

    @TableId(value = "parameter_id",type = IdType.AUTO)
    private Long parameterId;

    @TableField("parameter_name")
    private String parameterName;

    @TableField("parameter_weight")
    private Double parameterWeight;

    @TableField("scheme_id")
    private Integer schemeId;

    @TableField("is_delete")
    private Integer isDelete;

    public AssessParameter(String parameterName,Double parameterWeight,Integer schemeId){
        this.parameterName = parameterName;
        this.parameterWeight = parameterWeight;
        this.schemeId = schemeId;

    }
}
