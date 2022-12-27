package com.javaTraining.nobug.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SchemeResponseVo {

    private Long schemeId;
    private String schemeName;
    private String schemeVersion;
    private Date createTime;
    private Long projectClassId;
    private Integer importantDegree;
    private Integer isDelete;
    private Integer isDisable;
    private String projectClassName;
}
