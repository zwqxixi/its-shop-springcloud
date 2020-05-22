package com.its.basics.auth.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @className: CurrentUserController
 * @author: wenqin.zhao
 * @createDate: 2020/5/21 17:48
 * @description: TODO
 */
@RequestMapping("/api")
@RestController
public class UserController {


    /**
     * 用户认证
     *
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}

