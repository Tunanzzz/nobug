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
public class ProjectVo extends Composite{
    private Long projectId;
    private String projectName;
    private Long projectClassId;
    private String projectClassName;
    private Long userId;
    private Integer importantDegree;
    private Date scheduledStartTime;
    private Date scheduledEndTime;
    private Date actualStartTime;
    private Date actualEndTime;
    private Integer status;
    private Integer isDelete;
    private Double timeInDue;
    private Long schemeId;
    private String userName;
    private String userPortrait;
    private Double onTimeScore;     // 及时率得分
    private Double defectScore;     // 缺陷率得分
    private Double costTimeScore;   // 总人月得分
    private Double score;
    private String message;         // 项目考核留言
    private Double additionScore;   // 项目附加得分
}
