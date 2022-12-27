package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.javaTraining.nobug.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {

    public List<User> findAll();

}
