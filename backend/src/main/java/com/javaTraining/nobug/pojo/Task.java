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
@TableName("tasks")
public class Task {

    @TableId(value = "task_id",type = IdType.AUTO)
    private Long taskId;

    @TableField("task_name")
    private String taskName;

    @TableField("task_release_time")
    private Date taskReleaseTime;

    @TableField("project_id")
    private Long projectId;

    @TableField("plan_time")
    private Double planTime;

    @TableField("cost_time")
    private Double costTime;

    @TableField("user_id")
    private Long userId;

    @TableField("important_degree")
    private Integer importantDegree;

    @TableField("status")
    private Integer status;     //0 未解决,1 已解决


    @TableField("is_delete")
    private Integer isDelete;

}
