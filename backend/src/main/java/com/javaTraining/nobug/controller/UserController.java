package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.*;
import com.javaTraining.nobug.mapper.UserMapper;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.pojo.UserRole;
import com.javaTraining.nobug.service.PermissionService;
import com.javaTraining.nobug.service.RoleService;
import com.javaTraining.nobug.service.UserService;
import com.javaTraining.nobug.service.UserRoleService;
import com.javaTraining.nobug.vo.UserRequestVo;
import com.javaTraining.nobug.vo.UserResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javafx.scene.canvas.GraphicsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 周培铖
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/user")
@Api(value="用户接口",tags="用户管理相关的接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService user_roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("login")
    @ResponseBody
    public OutputObject login(@RequestBody UserRequestVo user){
//        System.out.println("========" + userName + "===" + userPassword);
//        UserRequestVo user = new UserRequestVo(userName,userPassword);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(user.getUserName() != null, "user_name", user.getUserName());
        queryWrapper.eq("is_delete", 0);
        User users = userService.getOne(queryWrapper);
        // 通过用户名从数据库中查询出该用户
        if (users == null){
            return new OutputObject(ReturnCode.FAIL,"用户名或密码错误",user);
        }
        // 密码校验
        String s = (MD5Utils.md5(user.getUserPassword() + users.getSalt()));
        if (users.getUserPassword().equals(s)==false){
            return new OutputObject(ReturnCode.FAIL,"用户名或密码错误",user);
        }
        queryWrapper.in(user.getUserPassword() != null, "user_password", s);
        String token = TokenUtil.sign(new User(user.getUserName(),s));
        HashMap<String,Object> queryHashMap = new HashMap<>();
        List<Role> rolesList = roleService.getRolesByUserId(users.getUserId());
        HashMap<String,Object> hs =new HashMap<>();
        hs.put("token",token);
        hs.put("userId",users.getUserId());
        hs.put("userName",users.getUserName());
        hs.put("roles",rolesList);
        hs.put("userPortrait",users.getUserPortrait());
        hs.put("registerTime",users.getRegisterTime());
        hs.put("permissions",permissionService.getPermissionsByUserid(users.getUserId()));
        System.out.println(permissionService.getPermissionsByUserid(users.getUserId()));
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",hs);

    }
    @PostMapping("register")
    public ResultObj register(@RequestBody UserRequestVo userss) {
        try {
            User user = new User(userss.getUserName(), userss.getUserPassword());
            // 查询用户名是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//            System.out.println("user_name====" + user.getUserPassword());
            queryWrapper.eq("user_name",user.getUserName());
            User users = userService.getOne(queryWrapper);
            if (users!=null){
                return ResultObj.THE_USER_ALREADY_EXISTS;
            }
            // 设置盐
            String salt = UUIDUtils.getUUID();
            user.setSalt(salt);
            // 设置密码加密
            String s = MD5Utils.md5(user.getUserPassword()+salt);
            // 设置用户默认头像
            user.setUserPassword(s);
            user.setUserPortrait(ReturnCode.DEFAULT_IMG_USER);
            System.out.println("user===="+user);
            userService.save(user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @GetMapping("/index")
    public String index() {
        return "欢迎来到Nobug管理系统";
    }

    @GetMapping("/getUserListByPage")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", dataType = "int")
    })
    public OutputObject getUserListByPage(@RequestHeader(name = "token") String token,@ApiIgnore @RequestParam Map<String ,Object> map) {
        Object pageNum = map.get("pageNum");
        Object pageSize = map.get("pageSize");
        if (pageNum != null && pageSize != null){
            map.replace("pageNum",Integer.parseInt(pageNum.toString()));
            map.replace("pageSize",Integer.parseInt(pageSize.toString()));
        }
        List<UserResponseVo> users = userMapper.getUserListByPage(map);
        for(UserResponseVo user:users){
            List<Role> rolesList = roleService.getRolesByUserId(user.getUserId());
            user.setRoles(rolesList);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Integer count = userMapper.selectCount(queryWrapper);
        HashMap<String,Object> hs = new HashMap<String,Object>();
        hs.put("userList",users);
        hs.put("totalCount",count);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",hs);
    }

    @PostMapping("/removeUserById")
    public ResultObj removeUserById(@RequestHeader(name = "token") String token,@RequestBody UserRequestVo userss ){
        User user = userMapper.selectById(userss.getUserId());
        if(user.getIsDelete() == 0){
            user.setIsDelete(1);
            userMapper.updateById(user);
            return ResultObj.DELETE_SUCCESS;
        } else {
            user.setIsDelete(0);
            userMapper.updateById(user);
            return ResultObj.RESET_SUCCESS;
        }
    }

    @PostMapping("/updateUserById")
    public ResultObj updateUserById(@RequestHeader(name = "token") String token,@RequestBody UserRequestVo userss ){
        System.out.println("userss-"+userss);
        //检查角色是否可用
        for(Role role:userss.getRoles()){
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("role_id",role.getRoleId());
            Role roleEntity = roleService.getOne(roleQueryWrapper);
            if(roleEntity.getIsDelete() == 1){
                return ResultObj.UPDATE_FAIL;
            }
        }
        //通过角色检查，开始更新表
        User user = userMapper.selectById(userss.getUserId());
        user.setUserName(userss.getUserName());
        userMapper.updateById(user);
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<UserRole>();
        userRoleQueryWrapper.eq("user_id",userss.getUserId());
        user_roleService.remove(userRoleQueryWrapper);
        for(Role role:userss.getRoles()){
            UserRole userRole = new UserRole();
            userRole.setUserId(userss.getUserId());
            userRole.setRoleId(role.getRoleId());
            GraphicsContext user_RoleMapper;
            user_roleService.save(userRole);
        }
        return ResultObj.UPDATE_SUCCESS;

    }
}


