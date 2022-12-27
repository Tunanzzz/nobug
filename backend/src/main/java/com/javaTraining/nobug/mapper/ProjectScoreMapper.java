package com.javaTraining.nobug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaTraining.nobug.pojo.ProjectScore;
import com.javaTraining.nobug.vo.ProjectScoreRequestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ProjectScoreMapper extends BaseMapper<ProjectScore> {
    @Update("update project_score set message=#{message} , addition_score=#{additionScore} " +
            ", review_user_id=#{reviewUserId} " +
            "where project_id = #{projectId}")
    public Boolean commitReviewProject(Map<String,Object> map);
}
