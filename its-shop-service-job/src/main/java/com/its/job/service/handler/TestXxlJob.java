package com.its.job.service.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestXxlJob
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/14 14:45
 * @Description: 基于bean模式开发定时任务 每个任务只需要对应一个方法  并添加@XxlJob注解
 * 支持自动扫描注入执行容器（多个JobHandler）
 */
@Component
public class TestXxlJob {

    @XxlJob(value = "TestJobHandler1")
    public ReturnT<String> execute(String param) throws InterruptedException{
        XxlJobLogger.log("开始执行任务");
        for(int i=0;i<5;i++){
            XxlJobLogger.log("执行次数:count:"+i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }
}
