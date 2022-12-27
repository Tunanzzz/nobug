package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.Task;
import com.javaTraining.nobug.vo.TaskResponseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper extends BaseMapper<Task> {

    @Select("select t.task_id,t.task_name,t.task_release_time,t.plan_time,t.cost_time," +
            "t.user_id,t.important_degree,t.status,t.project_id,p.project_name,p.project_class_id, " +
            "pc.project_class_name,u.user_name,u.user_portrait " +
            "from tasks as t,users as u,projects as p,project_class as pc " +
            "where t.is_delete=0 AND t.project_id=p.project_id AND " +
            "t.user_id = u.user_id AND t.project_id=#{projectId} " +
            " AND p.project_class_id=pc.project_class_id;")
    List<TaskResponseVo> selectTaskByProjectId(Long projectId);
}
