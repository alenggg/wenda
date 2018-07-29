package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来传递对象和视图的中间的一个对象
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
