package com.demo.softdreams.shared.service;

import java.lang.reflect.Field;
import java.util.List;

public interface Trimmable {

    default void trim() {
        Class c = this.getClass();
        while (c != null && c.getDeclaredFields().length > 0) {
            for (Field field : c.getDeclaredFields()) {
                trimProcess(field);
            }
            c = c.getSuperclass();
        }
    }

    default void trimWithIgnoreFields(List<String> ignoreList){
        Class c = this.getClass();
        while (c != null && c.getDeclaredFields().length > 0) {
            for (Field field : c.getDeclaredFields()) {
                if (!ignoreList.contains(field.getName())) {
                    trimProcess(field);
                }
            }
            c = c.getSuperclass();
        }
    }

    default void trimProcess(Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(this);
            if (value != null && value instanceof String){
                String trimmed = (String) value;
                field.set(this, trimmed.trim());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
