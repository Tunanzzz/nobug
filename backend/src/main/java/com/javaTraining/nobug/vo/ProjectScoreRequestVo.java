package com.javaTraining.nobug.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectScoreRequestVo {
    private Long projectId;
    private String message;
    private Double additionScore;
    private Long reviewUserId;

    public ProjectScoreRequestVo(Long projectId){
        this.projectId = projectId;
    }
}
