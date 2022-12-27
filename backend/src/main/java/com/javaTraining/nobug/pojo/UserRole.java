package com.javaTraining.nobug.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("user_role")
@EqualsAndHashCode(callSuper= false)
@Accessors(chain = true)
@NoArgsConstructor
public class UserRole implements Serializable {

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
