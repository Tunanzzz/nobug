package com.javaTraining.nobug.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("users")
@EqualsAndHashCode(callSuper= false)
@Accessors(chain = true)
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_portrait")
    private String userPortrait;

    @TableField("register_time")
    private Date registerTime;

    @TableField("is_delete")
    private Integer isDelete;

    @TableField("salt")
    private String salt;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
