package com.javaTraining.nobug.vo;

import com.javaTraining.nobug.pojo.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestVo {
    private Long roleId;
    private String roleName;
    private List<Permission> permissions;
}
