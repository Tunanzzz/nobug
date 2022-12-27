package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.Project;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.vo.ProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectVo> getProjectListByPage(Map<String,Object> map);

    @Select("select p.*,u.user_name,ps.on_time_score,ps.defect_score,ps.cost_time_score,pc.project_class_name," +
            "ps.message,ps.addition_score,ps.scheme_id,u.user_portrait " +
            " from projects as p,project_score as ps,users as u,project_class as pc" +
            " where p.project_id=ps.project_id " +
            " and u.user_id=p.user_id" +
            " and p.project_id=#{projectId}" +
            " and p.project_class_id=pc.project_class_id")
    ProjectVo getProjectById(Long projectId);

    List<Long> getProjectListByLimit(Map<String, Object> map);

    @Select("SELECT DISTINCT u.* FROM users u,projects p WHERE p.user_id = u.user_id")
    List<User> getProjectMasterUsers();
}
