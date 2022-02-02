package com.equator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Equator
 * @Date: 2020/11/1 22:01
 **/

public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 反序列化为对象
     * @param str
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String str, Class<T> classOfT) {
        if (str == null) {
            return null;
        }
        try {
            return mapper.readValue(str, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 反序列化为列表
     * @param str
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonList(String str, Class<T> classOfT) {
        if (str == null) {
            return null;
        }
        try {
            return mapper.readValue(str,
                    new TypeReference<List<T>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 序列化为Json
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmpty(JsonNode jsonNode, String memberName) {
        JsonNode value = jsonNode.findValue(memberName);
        return value == null || value.isNull();
    }

    /**
     * 将字符串解析为json对象
     *
     * @param str
     * @return
     */
    public static JsonNode toJsonObject(String str) {
        try {
            return mapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
