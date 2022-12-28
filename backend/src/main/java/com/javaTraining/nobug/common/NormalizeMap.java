package com.javaTraining.nobug.common;

import java.util.Map;

public class NormalizeMap {
    public static Map<String, Object> normalizeMapKeyName(Map<String, Object> map) {
        Object pageNum = map.get("pageNum");
        Object pageSize = map.get("pageSize");
        if(pageNum != null && pageSize != null){
            map.replace("pageNum",Integer.parseInt(pageNum.toString()));
            map.replace("pageSize",Integer.parseInt(pageSize.toString()));
        }
        return map;
    }
}
