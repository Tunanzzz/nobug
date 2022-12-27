package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.User;

import java.util.List;
import java.util.Map;

public interface  RoleService extends IService<Role> {

    public List<Role> getRolesByUserId(Long userId);
}
