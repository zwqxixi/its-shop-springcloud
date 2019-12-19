package com.its.shop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: WebApplication
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/18 20:20
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
