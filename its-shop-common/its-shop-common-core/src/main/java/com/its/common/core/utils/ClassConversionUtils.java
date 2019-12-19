package com.its.common.core.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: rdf-biz-common
 * @Auther: xuyong
 * @CreateDate: 2019-7-27 11:16
 * @Description: 实体类型转换,注意默认时间格式为：时间戳
 */
@Slf4j
public class ClassConversionUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static Map<String, DateFormat> DATE_FORMAT_MAP = new HashMap<>();

    public final static DateFormat Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static DateFormat YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static DateFormat Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateFormat YMD = new SimpleDateFormat("yyyyMMdd");

    public enum DateFormatTemplate {
        Y_M_D_H_M_S("Y_M_D_H_M_S"), YMDHMS("YMDHMS"), Y_M_D("Y_M_D"), YMD("YMD");

        DateFormatTemplate(String df) {
            this.value = df;
        }

        public String value;
    }

    static {
        //序列化的时候序列对象的所有属性
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        DATE_FORMAT_MAP.put("Y_M_D_H_M_S", Y_M_D_H_M_S);
        DATE_FORMAT_MAP.put("YMDHMS", YMDHMS);
        DATE_FORMAT_MAP.put("Y_M_D", Y_M_D);
        DATE_FORMAT_MAP.put("YMD", YMD);
    }

    /**
     * @Description: 对象转json
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 13:42
     */
    public static String toJson(Object so) throws Exception {
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(so);
        } catch (JsonProcessingException var) {
            log.debug("#toJson(), 非法对象json转换！");
            throw new Exception(var.getMessage(), var.getCause());
        }
        return json;
    }

    /**
     * @Description:对象转JSON
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 13:37
     */
    public static String toJson(Object so, @NotNull JsonInclude.Include include) throws Exception {
        String json = null;
        try {
            OBJECT_MAPPER.setSerializationInclusion(include);
            json = toJson(so);
        } finally {
            OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        }
        return json;
    }

    public static String toString(Object[] oa, String separator) throws Exception {
        String str = null;
        try {
            List<String> os = new ArrayList<>();
            for (int i = 0; i < oa.length; i++) {
                os.add(OBJECT_MAPPER.writeValueAsString(oa[i]));
            }
            str = StringUtils.join(os, separator);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return str;
    }

    /**
     * @Description: 对象转json
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 13:42
     */
    public static String toJson(Object so, DateFormatTemplate dateFormatTemplate, JsonInclude.Include include) throws Exception {
        String result = null;
        if (dateFormatTemplate != null) {
            try {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.FALSE);
                OBJECT_MAPPER.setDateFormat(DATE_FORMAT_MAP.get(dateFormatTemplate.value));
                if (include != null) {
                    result = toJson(so, include);
                } else {
                    result = toJson(so);
                }
            } finally {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.TRUE);
                OBJECT_MAPPER.setDateFormat(null);
            }
        } else
            result = include == null ? toJson(so) : toJson(so, include);
        return result;
    }

    /**
     * @Description : 对象转json
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 13:42
     */
    public static String toJson(Object so, DateFormatTemplate dateFormatTemplate) throws Exception {
        return toJson(so, dateFormatTemplate, null);
    }

    /**
     * @Description: json反序列化成对象
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:04
     */
    public static <T> T fromJson(String json, Class<T> tClass) throws Exception {
        T result = null;
        try {
            result = OBJECT_MAPPER.readValue(json, tClass);
        } catch (Exception var) {
            log.debug("#fromJson(), 非法json字符串！", var);
            throw new Exception(var.getMessage(), var.getCause());
        }
        return result;
    }

    /**
     * @Description: json反序列化成对象
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:04
     */
    public static <T> T fromJson(String json, Class<T> tClass, @NotNull JsonInclude.Include include) throws Exception {
        T result = null;
        try {
            OBJECT_MAPPER.setSerializationInclusion(include);
            result = fromJson(json, tClass);
        } finally {
            OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        }
        return result;
    }

    /**
     * @Description: json转对象
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:00
     */
    public static <T> T fromJson(String json, DateFormatTemplate dateFormatTemplate, Class<T> tClass, JsonInclude.Include include) throws Exception {
        T result = null;
        if (dateFormatTemplate != null) {
            try {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.FALSE);
                OBJECT_MAPPER.setDateFormat(DATE_FORMAT_MAP.get(dateFormatTemplate.value));
                if (include != null) {
                    result = fromJson(json, tClass, include);
                } else {
                    result = fromJson(json, tClass);
                }
            } finally {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.TRUE);
                OBJECT_MAPPER.setDateFormat(null);
            }
        } else
            result = include == null ? fromJson(json, tClass) : fromJson(json, dateFormatTemplate, tClass, include);
        return result;
    }

    /**
     * @Description: json转对象
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:00
     */
    public static <T> T fromJson(String json, DateFormatTemplate dateFormatTemplate, Class<T> tClass) throws Exception {
        return fromJson(json, dateFormatTemplate, tClass, null);
    }

    /**
     * @Description: 单实体类型转换
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 11:20
     */
    public static <T> T conversion(Object so, DateFormatTemplate dateFormatTemplate, Class<T> tClass) throws Exception {
        return fromJson(toJson(so, dateFormatTemplate), dateFormatTemplate, tClass);
    }

    /**
     * @Description: 单实体类型转json
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:13
     */
    public static <T> T conversion(Object so, Class<T> tClass) throws Exception {
        return fromJson(toJson(so), tClass);
    }

    /**
     * @Description: 集合实体类型转换
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:25
     */
    public static <S, T> List<T> conversionArray(Object so, Class<S> sClass, Class<T> tClass) throws Exception{
        return conversionArray(so, null, sClass, tClass);
    }

    /**
     * @Description: 集合实体类型转换
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 14:25
     */
    public static <S, T> List<T> conversionArray(Object so, Class<S> sClass, Class<T> tClass, JsonInclude.Include include) throws Exception {
        return conversionArray(so, null, sClass, tClass, include);
    }

    /**
     * @Description: 集合实体类型转换
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 11:31
     */
    public static <S, T> List<T> conversionArray(Object so, DateFormatTemplate dateFormatTemplate, Class<S> sClass, Class<T> tClass) throws Exception {
        return conversionArray(so, dateFormatTemplate, sClass, tClass, null);
    }

    /**
     * @Description: 集合实体类型转换
     * @Return:
     * @Auther: xuyong
     * @CreateDate: 2019-7-27 11:31
     */
    public static <S, T> List<T> conversionArray(Object so, DateFormatTemplate dateFormatTemplate, Class<S> sClass, Class<T> tClass, JsonInclude.Include include) throws Exception {
        List<T> result = null;
        String json = include == null ? toJson(so, dateFormatTemplate) : toJson(so, dateFormatTemplate, include);
        if (dateFormatTemplate != null) {
            try {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.FALSE);
                OBJECT_MAPPER.setDateFormat(DATE_FORMAT_MAP.get(dateFormatTemplate.value));
                result = OBJECT_MAPPER.readValue(json, getJavaType(sClass, tClass));
            } catch (Exception var) {
                log.debug("#conversionArray(), 非法对象转换！", var);
                throw new Exception(var.getMessage(), var.getCause());
            } finally {
                OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.TRUE);
                OBJECT_MAPPER.setDateFormat(null);
            }
        } else {
            try {
                result = OBJECT_MAPPER.readValue(json, getJavaType(sClass, tClass));
            } catch (IOException var) {
                log.debug("#conversionArray(), 非法对象转换！", var);
                throw new Exception(var.getMessage(), var.getCause());
            } finally {
                if (include != null)
                    OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            }
        }
        return result;
    }


    private static <S, T> JavaType getJavaType(Class<S> sClass, Class<T> tClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(sClass, tClass);
        return javaType;
    }
}