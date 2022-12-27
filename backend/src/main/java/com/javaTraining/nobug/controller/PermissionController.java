package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.mapper.PermissionMapper;
import com.javaTraining.nobug.mapper.UserMapper;
import com.javaTraining.nobug.pojo.Permission;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.PermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@Api(value = "权限接口",tags = "权限相关的接口")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/getAllPermission")
    private OutputObject getAllPermission(@RequestHeader String token){
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete",0);
        List<Permission> permissionList = permissionMapper.selectList(queryWrapper);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",permissionList);
    }

    @GetMapping("/getPermissionByUserId")
    private OutputObject getPermissionByUserId(@RequestParam Long userId,@RequestHeader String Token){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("user_id",userId);
        userQueryWrapper.in("is_delete",0);
        if(userMapper.selectOne(userQueryWrapper) != null){
            return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",permissionService.getPermissionsByUserid(userId));
        }else{
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"用户不存在或已删除",null);
        }
    }
}
