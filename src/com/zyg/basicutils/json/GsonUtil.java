//package com.zyg.basicutils.json;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import java.util.List;
//import java.util.Map;
//
///**
// * 该工具类依赖于Gson，对Gson进行了简单的封装
// */
//public class GsonUtil {
//    private static Gson gson;
//
//    public GsonUtil() {
//    }
//
//    public static Gson build() {
//        if(gson == null) {
//            gson = (new GsonBuilder()).create();
//        }
//
//        return gson;
//    }
//
//    public static String toJson(Object o) {
//        return build().toJson(o);
//    }
//
//    public static <T> T fromJsonToBean(String json, Class<T> clazz) {
//        return build().fromJson(json, clazz);
//    }
//
//    public static <T> List<T> fromJsonToBeanList(String json, Class<T> clazz) {
//        return (List)build().fromJson(json, (new TypeToken<List<T>>() {
//        }).getType());
//    }
//
//    public static <T> List<Map<String, T>> fromJsonToMapList(String json) {
//        return (List)build().fromJson(json, (new TypeToken<List<Map<String, T>>>() {
//        }).getType());
//    }
//
//    public static <T> Map<String, T> fromJsonToMap(String json) {
//        return (Map)build().fromJson(json, (new TypeToken<Map<String, T>>() {
//        }).getType());
//    }
//
//    public static void main(String[] args) {
//    }
//}
//
