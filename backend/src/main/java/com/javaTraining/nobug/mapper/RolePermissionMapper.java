package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    //根据rolePermission添加数据
    public void addRolePermission(RolePermission role_permission);

    //根据roleId删除所有对应权限
    public void deleteRolePermissionByRoleId(Long RoleId);


}
