package com.javaTraining.nobug.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("project_score")
public class ProjectScore {

    @TableId(value = "score_id",type = IdType.AUTO)
    private Long scoreId;

    @TableField(value = "project_id")
    private Long projectId;

    @TableField("on_time_score")
    private Double onTimeScore;     // 及时率得分

    @TableField("defect_score")
    private Double defectScore;     // 缺陷率得分

    @TableField("cost_time_score")
    private Double costTimeScore;   // 总人月得分

    @TableField("message")
    private String message;         // 项目考核留言

    @TableField("addition_score")
    private Double additionScore;   // 项目附加得分

    @TableField(value = "scheme_id")
    private Long schemeId;

    @TableField(value = "review_user_id")
    private Long reviewUserId;

    @TableField(value = "review_time")
    private Date reviewTime;

    @TableField("is_delete")
    private Integer isDelete;

    public ProjectScore(Long projectId, Double onTimeScore, Double defectScore, Double costTimeScore, Long schemeId,Long reviewUserId){
        this.projectId = projectId;
        this.onTimeScore = onTimeScore;
        this.defectScore = defectScore;
        this.costTimeScore = costTimeScore;
        this.schemeId = schemeId;
        this.reviewUserId = reviewUserId;
    }
}
