package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.vo.RoleResponseVo;

import java.util.List;
import java.util.Map;

public interface  RoleService extends IService<Role> {

    List<Role> getRolesByUserId(Long userId);

    List<Role> getAllRoles();

    List<RoleResponseVo> getRoleListByPage(Map<String, Object> map);

    Integer selectCount(QueryWrapper<Role> queryWrapper);

    void addRole(Role role);

    Role selectById(Long roleId);
}
