package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.javaTraining.nobug.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseVo {

    private Long userId;

    private String userName;

    private String userPortrait;

    private Date registerTime;

    private List<Role> roles;

    private Integer isDelete;
}
