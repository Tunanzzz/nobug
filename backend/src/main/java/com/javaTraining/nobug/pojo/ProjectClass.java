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
@TableName("project_class")
public class ProjectClass {

    @TableId(value = "project_class_id",type = IdType.AUTO)
    private Long projectClassId;

    @TableField("project_class_name")
    private String projectClassName;

    @TableField("is_delete")
    private Integer isDelete;

}
