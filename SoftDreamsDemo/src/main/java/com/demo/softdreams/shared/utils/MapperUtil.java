package com.demo.softdreams.shared.utils;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

public class MapperUtil {
    private static final ModelMapper mapper = new ModelMapper();

    public static <T> T map(Object source, Type type){
        return mapper.map(source,type);
    }
}