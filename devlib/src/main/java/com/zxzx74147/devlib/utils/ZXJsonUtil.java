package com.zxzx74147.devlib.utils;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * Created by zhengxin on 16/1/10.
 */
public class ZXJsonUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T fromJsonString(String json, Class<? extends T> mClass) {
        try {
            return JSON.parseObject(json, mClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJsonString(String json, Type mType) {
        try {
            return JSON.parseObject(json, mType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
