package com.template.module_common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class Gson2ListParameterizedType implements ParameterizedType {

    private Class tClass;

    public Gson2ListParameterizedType(Class tClass) {
        this.tClass = tClass;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{tClass};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
