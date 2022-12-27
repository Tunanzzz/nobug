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
@TableName("defects")
public class Defect {

    @TableId(value = "defect_id",type = IdType.AUTO)
    private Long defectId;

    @TableField("defect_name")
    private String defectName;

    @TableField("defect_release_time")
    private Date defectReleaseTime;

    @TableField("defect_end_time")
    private Date defectEndTime;

    @TableField("project_id")
    private Long projectId;

    @TableField("user_id")
    private Long userId;

    @TableField("important_degree")
    private Integer importantDegree;

    @TableField("status")
    private Integer status;     //0 未解决,1 已解决

    @TableField("is_delete")
    private Integer isDelete;

}
