package com.javaTraining.nobug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaTraining.nobug.mapper.DefectMapper;
import com.javaTraining.nobug.pojo.Defect;
import com.javaTraining.nobug.service.DefectService;
import com.javaTraining.nobug.vo.DefectResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefectServiceImpl extends ServiceImpl<DefectMapper, Defect> implements DefectService {

    @Resource
    private DefectMapper defectMapper;

    @Override
    public List<DefectResponseVo> selectDefectByProjectId(Long projectId) {
        return defectMapper.selectDefectByProjectId(projectId);
    }
}
