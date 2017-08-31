//package com.zyg.basicutils.j2ee;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
///**
// * 该类用于处理Servlet的request，提取不同contentType的HttpServletRequest中的请求信息
// * 该类依赖于GsonUtil，可自行选择JSON工具
// */
//public class ServletUtil {
//
//    //处理request中的json信息--contentType=json
//    public static <T>T ConvertJsonToBean(HttpServletRequest httpServletRequest, Class<T> beanClass) throws IOException {
//        StringBuffer json =  new StringBuffer();
//        BufferedReader reader = httpServletRequest.getReader();
//        String line = null;
//        while((line = reader.readLine()) != null)
//            json.append(line);
//        return GsonUtil.fromJsonToBean(json.toString(),beanClass);
//    }
//
//    //处理request中的parameters--contentType=xxx-formed
//    public static <T>T ConvertParametersToBean(HttpServletRequest httpServletRequest,Class<T> beanClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//        Field[] fields = beanClass.getDeclaredFields(); // 获取实体类的所有属性
//        Set<Class> genericTypes = new LinkedHashSet();//存储实体类所有属性的类型
//        for (Field field : fields) {
//            genericTypes.add(field.getType());
//        }
//        Object object = beanClass.getConstructor().newInstance();//构造一个该实体类的对象
//        for (int j = 0; j < fields.length; j++) { // 遍历所有属性
//            String originKey = fields[j].getName(); // 获取属性的名字
//            String type = fields[j].getType().getName(); // 获取属性的类型
//            for (Class genericType : genericTypes) {
//                if (type.equals(genericType.getName())) { // 如果type是类类型，则前面包含"class "，后面跟类名,且是该实体类的属性的类型中的一种
//                    Object value = httpServletRequest.getParameter(originKey); // 获取属性值
//                    if(value != null){
//                        Method m = beanClass.getMethod("set" + originKey.substring(0, 1).toUpperCase() + originKey.substring(1),genericType);//获取设置属性值的方法
//                        m.invoke(object, value);//执行设置属性值的方法来设置属性
//                    }
//                }
//            }
//        }
//        return (T)object;
//    }
//}
