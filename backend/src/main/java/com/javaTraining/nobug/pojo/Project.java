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
@TableName("projects")
public class Project {

    @TableId(value = "project_id", type = IdType.AUTO)
    private Long projectId;

    @TableField(value = "project_name")
    private String projectName;

    @TableField(value = "project_class_id")
    private Long projectClassId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "important_degree")
    private Integer importantDegree;

    @TableField(value = "scheduled_start_time")
    private Date scheduledStartTime;

    @TableField(value = "scheduled_end_time")
    private Date scheduledEndTime;

    @TableField(value = "actual_start_time")
    private Date actualStartTime;

    @TableField(value = "actual_end_time")
    private Date actualEndTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(value = "time_in_due")
    private Double timeInDue;




}
