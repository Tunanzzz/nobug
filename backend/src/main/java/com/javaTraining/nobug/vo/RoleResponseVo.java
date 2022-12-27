package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.javaTraining.nobug.pojo.Permission;
import com.javaTraining.nobug.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseVo {

    private Long roleId;

    private String roleName;

    private Integer isDelete;

    private List<Permission> permissions;

    RoleResponseVo(Long roleId,String roleName,Integer isDelete){
        this.roleId=roleId;
        this.roleName=roleName;
        this.isDelete=isDelete;
    }
}
