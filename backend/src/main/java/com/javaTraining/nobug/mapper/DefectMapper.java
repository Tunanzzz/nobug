package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.Defect;
import com.javaTraining.nobug.vo.DefectResponseVo;
import com.javaTraining.nobug.vo.TaskResponseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DefectMapper extends BaseMapper<Defect> {
    @Select("select d.defect_id,d.defect_name,d.defect_start_time,d.defect_end_time," +
            "d.user_id,d.important_degree,d.status,d.project_id,p.project_name,p.project_class_id, " +
            "pc.project_class_name,u.user_name,u.user_portrait " +
            "from defects as d,users as u,projects as p,project_class as pc " +
            "where d.is_delete=0 AND d.project_id=p.project_id AND " +
            "d.user_id = u.user_id AND d.project_id=#{projectId} " +
            " AND p.project_class_id=pc.project_class_id;")
    List<DefectResponseVo> selectDefectByProjectId(Long projectId);
}
