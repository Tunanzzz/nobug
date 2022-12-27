package com.javaTraining.nobug.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("role_permission")
@EqualsAndHashCode(callSuper= false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable {

    @TableField("role_id")
    private Long roleId;

    @TableField("permission_id")
    private Long permissionId;

}
