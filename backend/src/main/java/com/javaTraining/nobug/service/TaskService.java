package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.Task;
import com.javaTraining.nobug.vo.TaskResponseVo;

import java.util.List;

public interface TaskService extends IService<Task> {
    List<TaskResponseVo> selectTaskByProjectId(Long projectId);
}
