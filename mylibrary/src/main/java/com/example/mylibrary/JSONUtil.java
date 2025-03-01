package com.example.mylibrary;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON工具类
 */
public class JSONUtil {
    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        //驼峰转下划线
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        return gson;
    }
}
