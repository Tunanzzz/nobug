package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.service.RoleService;
import com.javaTraining.nobug.mapper.RoleMapper;
import com.javaTraining.nobug.vo.RoleResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public List<RoleResponseVo> getRoleListByPage(Map<String, Object> map) {
        return roleMapper.getRoleListByPage(map);
    }

    @Override
    public Integer selectCount(QueryWrapper<Role> queryWrapper) {
        return roleMapper.selectCount(queryWrapper);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    @Override
    public Role selectById(Long roleId) {
        return roleMapper.selectById(roleId);
    }
}