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
@TableName("thresholds")
public class Threshold {

    @TableId(value = "threshold_id",type = IdType.AUTO)
    private Long thresholdId;

    @TableField("parameter_id")
    private Long parameterId;

    @TableField("parameter_value")
    private Double parameterValue;

    @TableField("parameter_score")
    private Double parameterScore;

    @TableField("is_delete")
    private Integer isDelete;


    public Threshold(Long parameterId, Double parameterValue, Double parameterScore) {
        this.parameterId = parameterId;
        this.parameterScore = parameterScore;
        this.parameterValue = parameterValue;
    }
}
