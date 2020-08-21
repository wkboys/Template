package com.template.module_common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonUtils {
    public static <T> T getObjectFromJson(String json, Class<T> claz) {
        Gson gson = new Gson();
        T b;
        try {
            b = gson.fromJson(json, claz);
        } catch (JsonSyntaxException ex) {
            return null;
        }
        return b;
    }

    public static String getObject2Json(Object claz) {
        Gson gson = new Gson();
        String str = gson.toJson(claz);
        return str;
    }

    public static <T> T parse(String json, Class<T> claz) {
        Gson gson = new Gson();
        T b = gson.fromJson(json, claz);
        return b;
    }
}
