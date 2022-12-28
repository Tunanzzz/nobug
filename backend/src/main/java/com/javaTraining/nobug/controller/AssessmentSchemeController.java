package com.javaTraining.nobug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ObjectArrays;
import com.javaTraining.nobug.common.NormalizeMap;
import com.javaTraining.nobug.common.OutputObject;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.mapper.AssessParameterMapper;
import com.javaTraining.nobug.mapper.AssessmentSchemeMapper;
import com.javaTraining.nobug.mapper.ThresholdMapper;
import com.javaTraining.nobug.pojo.AssessParameter;
import com.javaTraining.nobug.pojo.AssessmentScheme;
import com.javaTraining.nobug.pojo.Threshold;
import com.javaTraining.nobug.pojo.User;
import com.javaTraining.nobug.service.AssessParameterService;
import com.javaTraining.nobug.service.AssessmentSchemeService;
import com.javaTraining.nobug.service.ThresholdService;
import com.javaTraining.nobug.vo.AssessSchemeVo;
import com.javaTraining.nobug.vo.ThresholdVo;
import com.javaTraining.nobug.vo.UserResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

import static com.google.common.collect.ObjectArrays.concat;

@RestController
@RequestMapping("/assessmentScheme")
@Api(value="评估方案接口",tags="评估方案相关的接口")
@Slf4j
public class AssessmentSchemeController {

    @Autowired
    private AssessmentSchemeService assessmentSchemeService;


    @Autowired
    private AssessmentSchemeMapper assessmentSchemeMapper;


    @Autowired
    private AssessParameterService assessParameterService;
    @Autowired
    private AssessParameterMapper assessParameterMapper;

    @Autowired
    private ThresholdService thresholdService;

    @Autowired
    private ThresholdMapper thresholdMapper;

    @GetMapping("/getSchemeById")
    @ApiOperation(value = "通过id获取评估方案的详细信息", notes = "返回该方案的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schemeId", value = "方案ID", paramType = "query", dataType = "Long"),
    })
    private OutputObject GetSchemeById(@ApiIgnore @RequestParam Long schemeId, @RequestHeader String token) {
        AssessSchemeVo assessSchemeVo = assessmentSchemeService.getSchemeById(schemeId);
        if (assessSchemeVo == null) {
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()), "评估方案不存在或已被删除", null);
        }
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", assessmentSchemeService.getSchemeById(schemeId));
    }

    @GetMapping("/getSchemesListByPage")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", dataType = "int")
    })
    private OutputObject GetSchemesListByPage(@ApiIgnore @RequestParam Map<String, Object> map, @RequestHeader String token) {
        map = NormalizeMap.normalizeMapKeyName(map);
        List<UserResponseVo> assessmentScheme = assessmentSchemeMapper.getSchemeListByPage(map);
        QueryWrapper<AssessmentScheme> queryWrapper = new QueryWrapper<>();
        Integer count = assessmentSchemeMapper.selectCount(queryWrapper);
        HashMap<String, Object> hs = new HashMap<String, Object>();
        hs.put("schemeList", assessmentScheme);
        hs.put("totalCount", count);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", hs);
    }


    @PostMapping("/insertAssessmentScheme")
    @ApiOperation(value = "通过传入数据增加评估方案", notes = "返回是否创建成功")
    private ResultObj insertAssessmentScheme(@RequestHeader(name = "token") String token, @RequestBody AssessSchemeVo assessSchemeVo) {
        try {
            AssessmentScheme assessmentScheme = new AssessmentScheme(assessSchemeVo.getSchemeName(), assessSchemeVo.getSchemeVersion(),
                    assessSchemeVo.getCreateTime(), assessSchemeVo.getProjectClassId(),
                    assessSchemeVo.getImportantDegree());
            assessmentSchemeService.save(assessmentScheme);
            for (AssessParameter assessParameter : assessSchemeVo.getAssessParameterList()) {
                assessParameter.setSchemeId(Math.toIntExact(assessmentScheme.getSchemeId()));
                assessParameterService.save(assessParameter);

                for (ThresholdVo thresholdVo : assessSchemeVo.getThresholdVoList()) {
                    if (thresholdVo.getParameterName().equals(assessParameter.getParameterName())) {
                        QueryWrapper<AssessParameter> assessParameterQueryWrapper = new QueryWrapper<AssessParameter>();
                        assessParameterQueryWrapper.eq("parameter_name", assessParameter.getParameterName());
                        assessParameterQueryWrapper.eq("scheme_id", assessParameter.getSchemeId());
                        thresholdVo.setParameterId(assessParameterMapper.selectOne(assessParameterQueryWrapper).getParameterId());
                        Threshold threshold = new Threshold(thresholdVo.getParameterId(), thresholdVo.getParameterValue(), thresholdVo.getParameterScore());
                        thresholdService.save(threshold);
                    } else {

                    }

                }
            }
            return ResultObj.ADD_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @PostMapping("/disableSchemeById")
    @ApiOperation(value = "禁用/启用一个评估方案")
    private OutputObject disableSchemeById(@RequestBody Map<String,Object> reqMap,@RequestHeader String token){
        QueryWrapper<AssessmentScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("scheme_id",reqMap.get("schemeId"));
        queryWrapper.in("is_delete",0);
        AssessmentScheme assessmentScheme = assessmentSchemeMapper.selectOne(queryWrapper);
        if(assessmentScheme == null){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"评估方案不存在或已删除",null);
        }else {
            Integer disable = 1 - assessmentScheme.getIsDisable();
            assessmentScheme.setIsDisable(disable);
            assessmentSchemeMapper.updateById(assessmentScheme);
            return new OutputObject(String.valueOf(HttpStatus.OK.value()),"修改成功",null);
        }
    }

    @PostMapping("/deleteSchemeById")
    @ApiOperation(value = "删除/恢复一个评估方案")
    private OutputObject deleteSchemeById(@RequestBody Map<String,Object> reqMap,@RequestHeader String token){
        QueryWrapper<AssessmentScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("scheme_id",reqMap.get("schemeId"));
        AssessmentScheme assessmentScheme = assessmentSchemeMapper.selectOne(queryWrapper);
        if(assessmentScheme == null){
            return new OutputObject(String.valueOf(HttpStatus.BAD_REQUEST.value()),"评估方案不存在",null);
        }else {
            Integer delete = 1 - assessmentScheme.getIsDelete();
            assessmentScheme.setIsDelete(delete);
            assessmentSchemeMapper.updateById(assessmentScheme);
            return new OutputObject(String.valueOf(HttpStatus.OK.value()),"修改成功",null);
        }
    }

    @GetMapping("/getSchemeByProjectClassId")
    @ApiOperation(value = "通过项目类型获取评估方案的详细信息", notes = "返回该项目类型的方案的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectClassId", value = "项目类型ID", paramType = "query", dataType = "Long"),
    })
    private OutputObject getSchemeByProjectClassId(@ApiIgnore @RequestParam Long projectClassId, @RequestHeader String token) {
        QueryWrapper<AssessmentScheme> assessmentSchemeQueryWrapper = new QueryWrapper<>();
        assessmentSchemeQueryWrapper.in("project_class_id",projectClassId);
        assessmentSchemeQueryWrapper.in("is_delete",0);
        List<AssessSchemeVo> assessSchemeVoList = new ArrayList<>();
        List<AssessmentScheme> assessmentSchemes = assessmentSchemeMapper.selectList(assessmentSchemeQueryWrapper);
        for(AssessmentScheme assessmentScheme : assessmentSchemes){
            assessSchemeVoList.add(assessmentSchemeService.getSchemeById(assessmentScheme.getSchemeId()));
        }
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", assessSchemeVoList);
    }
}