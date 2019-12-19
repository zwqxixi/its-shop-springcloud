package com.its.common.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:09
 * @Description: Redis操作工具类
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void setString(String key, String data, Long timeout) {
        stringRedisTemplate.opsForValue().set(key, data);
        if (timeout != null) {
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }
    public void setString(String key, String data) {
        setString(key, data, null);
    }

    public String getString(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    public Boolean delKey(String key) {
        return stringRedisTemplate.delete(key);
    }

    //开启事务
    public void multi(){
        stringRedisTemplate.setEnableTransactionSupport(true);
        stringRedisTemplate.multi();
    }
    //取消事务
    public void discard(){
        stringRedisTemplate.discard();
    }
    //提交事务
    public void exec(){
        stringRedisTemplate.exec();
    }



}
