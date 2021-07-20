package com.its.basics.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @className: ZipKinServerApplication
 * @author: wenqin.zhao
 * @createDate: 2021/1/5 18:44
 * @description: ZipKin server
 */
@SpringBootApplication
@EnableEurekaClient
public class ZipKinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipKinServerApplication.class,args);
    }
}
