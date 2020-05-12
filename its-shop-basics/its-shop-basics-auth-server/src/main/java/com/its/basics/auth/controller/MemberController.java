package com.its.basics.auth.controller;

import com.its.basics.auth.config.auth.CustomUserDetailService;
import com.its.common.core.constant.ApiResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * @className: MemberController
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 23:54
 * @description: TODO
 */
@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @PostMapping("/currentUserInfo")
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
    }

    @PostMapping("/exit")
    public ApiResponseVo revokeToken(String access_token) {
        //注销当前用户
        if (consumerTokenServices.revokeToken(access_token)) {
            return ApiResponseVo.genCustom(0L,"注销成功",null);
        } else {
            return ApiResponseVo.genError(4L);
        }
    }
}
