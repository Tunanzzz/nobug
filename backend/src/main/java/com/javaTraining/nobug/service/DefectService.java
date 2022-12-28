package com.javaTraining.nobug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaTraining.nobug.pojo.Defect;
import com.javaTraining.nobug.vo.DefectResponseVo;

import java.util.List;


public interface DefectService extends IService<Defect> {
    List<DefectResponseVo> selectDefectByProjectId(Long projectId);
}
