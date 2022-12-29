package com.javaTraining.nobug.vo.abstractVo;

import com.javaTraining.nobug.common.ResultObj;

import java.util.List;

public abstract class Component {

    public abstract ResultObj operation();
    public abstract Boolean add(Component component);
    public abstract Component remove();
    public abstract List<Component> getChild();
}
