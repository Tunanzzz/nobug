package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.Role;
import com.javaTraining.nobug.vo.RoleResponseVo;
import com.javaTraining.nobug.vo.UserResponseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> getRolesByUserId(Long userId);

    public void addRole(Role role);

    @Select("select * from roles where is_delete=0")
    List<Role> getAllRoles();

    public List<RoleResponseVo> getRoleListByPage(Map<String,Object> map);
}

