package com.its.basics.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: AuthServerApplication
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 19:06
 * @description: 认证授权服务
 */
@SpringBootApplication
public class AuthServerApplication{

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
