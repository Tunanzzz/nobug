package com.javaTraining.nobug.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestVo {
    private Long roleId;
    private String roleName;
    private List<Long> permissionIdList;
}
