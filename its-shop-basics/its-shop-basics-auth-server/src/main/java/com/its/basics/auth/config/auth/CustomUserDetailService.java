package com.its.basics.auth.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * @className: CustomUserDetailService
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 20:07
 * @description: TODO
 */
public class CustomUserDetailService implements UserDetailsService {

    /**
      * @description: 根据用户名查找用户信息  返回用户信息实体
      * @param:
      * @return: 用于身份认证的 UserDetails 用户信息实体
      * @author: wenqin.zhao
      * @createDate: 20:09 2020/5/11
      */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
