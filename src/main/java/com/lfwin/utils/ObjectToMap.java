package com.lfwin.utils;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
public class ObjectToMap {
    public static Map<String, Object> convert(Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(object) != null ? field.get(object).toString() : "";
            map.put(field.getName(), value);
        }
        return map;
    }
}