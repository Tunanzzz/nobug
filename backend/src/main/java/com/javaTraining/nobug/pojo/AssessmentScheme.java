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
@TableName("assessment_scheme")
public class AssessmentScheme {

    @TableId(value = "scheme_id",type = IdType.AUTO)
    private Long schemeId;

    @TableField("scheme_name")
    private String schemeName;

    @TableField("scheme_version")
    private Double schemeVersion;

    @TableField("create_time")
    private Date createTime;

    @TableField("project_class_id")
    private Long projectClassId;

    @TableField("important_degree")
    private Integer importantDegree;

    @TableField("is_disable")
    private Integer isDisable;      //0 未禁用,1 禁用

    @TableField("is_delete")
    private Integer isDelete;

    public AssessmentScheme(String schemeName,Double schemeVersion,
                            Date createTime, Long projectClassId, Integer importantDegree){
        this.schemeName = schemeName;
        this.schemeVersion = schemeVersion;
        this.createTime = createTime;
        this.projectClassId = projectClassId;
        this.importantDegree = importantDegree;

    }
}
