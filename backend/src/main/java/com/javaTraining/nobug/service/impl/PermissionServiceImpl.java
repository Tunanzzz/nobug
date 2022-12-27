package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.PermissionMapper;
import com.javaTraining.nobug.mapper.RoleMapper;
import com.javaTraining.nobug.mapper.RolePermissionMapper;
import com.javaTraining.nobug.mapper.UserRoleMapper;
import com.javaTraining.nobug.pojo.Permission;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.RolePermission;
import com.javaTraining.nobug.pojo.UserRole;
import com.javaTraining.nobug.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getPermissionsByRoleId(Long roleId) {
        return permissionMapper.getPermissionsByRoleId(roleId);
    }

    @Override
    public List<Permission> getPermissionsByUserid(Long userId) {
        QueryWrapper<UserRole> roleQuery = new QueryWrapper();
        List<Permission> permissionList = new ArrayList<>();
        roleQuery.in("user_id",userId);
        List<UserRole> roleList = userRoleMapper.selectList(roleQuery);
        for(UserRole r : roleList){
            QueryWrapper<RolePermission> permissionQueryWrapper = new QueryWrapper<>();
            permissionQueryWrapper.in("role_id",r.getRoleId());
            List<RolePermission> selectRolePermisson = rolePermissionMapper.selectList(permissionQueryWrapper);
            List<Permission> selectPermisson = new ArrayList<>();
            for(RolePermission rp : selectRolePermisson){
                selectPermisson.add(permissionMapper.selectById(rp.getPermissionId()));
            }
            if(selectPermisson != null){
                HashSet set = new HashSet(selectPermisson);
                set.removeAll(permissionList);
                permissionList.addAll(set);
            }
        }
        return permissionList;
    }
}
