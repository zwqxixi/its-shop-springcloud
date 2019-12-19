package com.its.member.service.impl;

import com.its.member.service.feign.FeignWeiXinService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 20:44
 * @Description:
 */
@RestController
public class MemberServiceImpl {

    @Resource
    private FeignWeiXinService feignWeiXinService;

    /*@Override
    public TestEntity MemberToWeiXin() {
        return feignWeiXinService.getTestEntity();
    }*/
}
