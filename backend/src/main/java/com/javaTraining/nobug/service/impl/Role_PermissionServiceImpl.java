package com.javaTraining.nobug.service.impl;

import com.javaTraining.nobug.mapper.RolePermissionMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.javaTraining.nobug.service.RolePermissionService;

import com.javaTraining.nobug.pojo.RolePermission;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Role_PermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;
}
