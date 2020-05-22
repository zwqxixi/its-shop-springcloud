package com.its.basics.auth.config.resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @className: NoEncryptPasswordEncoder
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 23:51
 * @description: TODO
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //密码对比 密码对 true 反之 false
        //CharSequence 数据库中的密码
        //s 前台传入的密码
        return s.equals((String) charSequence);
    }
}
