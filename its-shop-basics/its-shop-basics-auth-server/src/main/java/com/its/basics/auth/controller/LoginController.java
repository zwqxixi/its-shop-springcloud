package com.its.basics.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: LoginController
 * @author: wenqin.zhao
 * @createDate: 2020/5/12 14:52
 * @description: TODO
 */
@Controller
public class LoginController {
    /**
     * 自定义登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
