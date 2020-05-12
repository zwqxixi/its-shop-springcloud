package com.its.basics.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * @className: UserController
 * @author: wenqin.zhao
 * @createDate: 2020/5/12 14:56
 * @description: 资源服务器受保护的接口
 */
@RestController
public class UserController {
    /**
     * 资源服务器提供的受保护接口
     * @param principal
     * @return
     */
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}
