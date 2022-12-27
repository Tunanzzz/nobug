package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThresholdVo {
    private Long thresholdId;

    private Long parameterId;

    private Double parameterValue;

    private Double parameterScore;

    private String parameterName;

}
