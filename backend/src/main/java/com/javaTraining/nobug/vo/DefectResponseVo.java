package com.javaTraining.nobug.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.vo.abstractVo.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefectResponseVo extends Component {
    private Long defectId;

    private String defectName;

    private Date defectStartTime;

    private Date defectEndTime;

    private Long projectId;

    private Long userId;

    private Integer importantDegree;

    private Integer status;     //0 未解决,1 已解决

    private String projectName;

    private Long projectClassId;

    private String projectClassName;

    private String userName;    // 任务承担者

    private String userPortrait;    // 用户头像

    @Override
    public ResultObj operation() {
        return null;
    }

    @Override
    public Boolean add(Component component) {
        return null;
    }

    @Override
    public Component remove() {
        return null;
    }

    @Override
    public List<Component> getChild() {
        return null;
    }
}
