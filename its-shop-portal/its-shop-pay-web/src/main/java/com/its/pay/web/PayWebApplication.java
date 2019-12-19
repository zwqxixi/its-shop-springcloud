package com.its.pay.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: PayWebApplication
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/19 11:49
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class PayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayWebApplication.class,args);
    }
}
