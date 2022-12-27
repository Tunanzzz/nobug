package com.javaTraining.nobug.vo;

import com.javaTraining.nobug.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestVo {
    private Long userId;
    private String userName;
    private List<Role> roles;
    private String userPassword;
}
