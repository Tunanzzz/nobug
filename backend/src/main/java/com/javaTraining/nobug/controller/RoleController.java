package com.javaTraining.nobug.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaTraining.nobug.common.*;
import com.javaTraining.nobug.pojo.Permission;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.pojo.RolePermission;
import com.javaTraining.nobug.service.PermissionService;
import com.javaTraining.nobug.service.RoleService;
import com.javaTraining.nobug.service.RolePermissionService;
import com.javaTraining.nobug.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @author 乔芊禾
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/role")
@Api(value="角色接口",tags="角色管理相关的接口")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    //获取全部角色
    @GetMapping("/getAllRoles")
    @ApiOperation(value = "获取全部角色",notes = "获取全部角色")
    public OutputObject getAllRoles(@RequestHeader(name = "token") String token){
        List<Role> roles = roleService.getAllRoles();
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",roles);
    }

    //分页获取角色
    @GetMapping("/getRoleListByPage")
    @ApiOperation(value = "分页获取角色",notes = "分页获取角色")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", dataType = "int")
    })
    public OutputObject getRoleListByPage(@RequestHeader(name = "token") String token,@ApiIgnore @RequestParam Map<String ,Object> map) {
        map = NormalizeMap.normalizeMapKeyName(map);
        List<RoleResponseVo> roles = roleService.getRoleListByPage(map);
        for(RoleResponseVo role:roles){
            List<Permission> permissionsList = permissionService.getPermissionsByRoleId(role.getRoleId());
            role.setPermissions(permissionsList);
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        Integer count = roleService.selectCount(queryWrapper);
        HashMap<String,Object> hs = new HashMap<>();
        hs.put("roleList",roles);
        hs.put("totalCount",count);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()),"成功",hs);
    }

    //增加一个角色（默认无权限）
    @PostMapping(value = "/addRole",produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "增加一个无权限角色",notes = "增加一个无权限角色")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "body", dataType = "String")
    })
    public ResultObj addRole(@RequestHeader(name = "token") String token, @ApiIgnore @RequestBody Map<String,String> map){
//        Object roleId = map.get("roleId");
        String roleName = map.get("roleName");
        System.out.println(roleName);
        Role role = new Role(roleName,0);
        System.out.println(role);
        roleService.addRole(role);
        return ResultObj.ADD_SUCCESS;
    }

    //删除一个角色
    @PostMapping("/deleteRoleById")
    @ApiOperation(value = "删除/恢复一个角色",notes = "删除/恢复角色")
    public ResultObj deleteRole(@RequestHeader(name = "token") String token, @RequestBody RoleRequestVo roles){
        Role role = roleService.selectById(roles.getRoleId());
        if(role == null){
            return ResultObj.DELETE_FAIL;
        }
        if(role.getIsDelete() == 0){
            role.setIsDelete(1);
            roleService.updateById(role);
            return ResultObj.DELETE_SUCCESS;
        }else {
            role.setIsDelete(0);
            roleService.updateById(role);
            return ResultObj.RESET_SUCCESS;
        }
    }

    //更新角色
    @PostMapping("/updateRoleById")
    @ApiOperation(value = "通过对象修改角色",notes = "通过对象修改角色")
    public ResultObj updateRoleById(@RequestHeader(name = "token") String token, @RequestBody RoleRequestVo roless){
        Role role = roleService.selectById(roless.getRoleId());//找到表中对应ID的角色
        if(role.getIsDelete() == 1 || role==null)//检查角色是否存在
            return ResultObj.UPDATE_FAIL;
        role.setRoleName(roless.getRoleName());//重新设置设置名称
        roleService.updateById(role);//更新表中角色（只是更新了角色名）
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();//创建一个查询用的wrapper
        rolePermissionQueryWrapper.eq("role_id",roless.getRoleId());//找到所有对应roless的Id的数据
        if(roless.getPermissions() == null){
            rolePermissionService.remove(rolePermissionQueryWrapper);//删掉原来的对应数据
        }else{
            for(Permission permission:roless.getPermissions()){
                rolePermissionService.remove(rolePermissionQueryWrapper);//删掉原来的对应数据
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roless.getRoleId());
                rolePermission.setPermissionId(permission.getPermissionId());
                GraphicsContext role_PermissionMapper;
                rolePermissionService.save(rolePermission);
            }
        }
        return ResultObj.UPDATE_SUCCESS;
    }

    //更新角色
    @PostMapping("/updateRoleByIdByArray")
    @ApiOperation(value = "通过参数修改角色",notes = "通过参数修改角色")
    public ResultObj updateRoleById2(@RequestHeader(name = "token") String token,
                                     @RequestBody PermissionRequestVo permissionRequestVo
//                                      @RequestParam(required = true) Long roleId,
//                                      @RequestParam(required = true) String roleName,
//                                      @RequestParam(required = true) List<Long> permissionIdList
    ){
        //根据Id更新角色和角色名
        Role role = roleService.selectById(permissionRequestVo.getRoleId());//找到表中对应ID的角色
        if(role.getIsDelete() == 1 || role==null)//检查角色是否存在
            return ResultObj.UPDATE_FAIL;
        role.setRoleName(permissionRequestVo.getRoleName());//重新设置设置名称
        roleService.updateById(role);//更新表中角色（只是更新了角色名）

        //更新角色权限
        //1. 在role_permission表中找到对应该roleId的所有权限并删除
        rolePermissionService.deleteRolePermissionByRoleId(permissionRequestVo.getRoleId());
        //2. 循环遍历permissionIdList列表，在role_permission表中添加permissionId的数据
        for(Long permissionId:permissionRequestVo.getPermissionIdList()){
            rolePermissionService.addRolePermission(new RolePermission(permissionRequestVo.getRoleId(),permissionId));
        }
        return ResultObj.UPDATE_SUCCESS;
    }
}
