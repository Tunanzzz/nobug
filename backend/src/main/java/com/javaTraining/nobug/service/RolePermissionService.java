package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.RolePermission;

public interface RolePermissionService extends IService<RolePermission> {

    void deleteRolePermissionByRoleId(Long roleId);

    void addRolePermission(RolePermission rolePermission);
}
