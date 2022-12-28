package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.TaskMapper;
import com.javaTraining.nobug.pojo.Task;
import com.javaTraining.nobug.service.TaskService;
import com.javaTraining.nobug.vo.TaskResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public List<TaskResponseVo> selectTaskByProjectId(Long projectId) {
        return taskMapper.selectTaskByProjectId(projectId);
    }
}
