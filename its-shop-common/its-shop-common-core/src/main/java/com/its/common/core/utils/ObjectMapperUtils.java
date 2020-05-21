package com.its.common.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ObjectMapperUtils {


    public static <T> T toObject(String source, Class<T> t) {
        if (StringUtils.isBlank(source)) {
            log.debug("source 为空");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(source, t);
        } catch (Exception ex) {
            log.info("json 转化异常 {}", ex);
        }
        return null;
    }

    public static <T> String toString(T source) {
        if (null == source) {
            log.debug("source 为空");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(source);
        } catch (Exception ex) {
            log.info("json 转化异常 {}", ex);
        }
        return null;
    }

}
