package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.Permission;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.User;

import java.util.List;
import java.util.Map;

public interface  PermissionService extends IService<Permission> {

    public List<Permission> getPermissionsByRoleId(Long roleId);

    public List<Permission> getPermissionsByUserid(Long userId);
}
