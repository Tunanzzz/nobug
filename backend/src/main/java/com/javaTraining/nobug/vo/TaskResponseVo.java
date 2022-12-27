package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseVo {
    private Long taskId;

    private String taskName;

    private Date taskReleaseTime;

    private Long projectId;

    private Double planTime;

    private Double costTime;

    private Long userId;

    private Integer importantDegree;

    private Integer status;     //0 未解决,1 已解决

    private String projectName;

    private Long projectClassId;

    private String projectClassName;

    private String userName;    // 任务承担者

    private String userPortrait;    // 用户头像

}
