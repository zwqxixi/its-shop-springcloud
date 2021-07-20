//package com.its.member.service.config.security;
//
//import com.its.member.service.config.security.MyUserDetailService;
//import com.its.member.service.config.security.NoEncryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @className: WebSecurityConfig
// * @author: wenqin.zhao
// * @createDate: 2020/5/20 15:46
// * @description: TODO
// */
//@EnableWebSecurity
//@Configuration
//// @Order(2)
//// ResourceServerConfig @Order值默认为3  WebSecurityConfig @Order值默认为100
//// 引入SpringSecurity模块后 ***Adapter 每个Adapter对应一个filterChain
//// SpringSecurity在对资源URL进行过滤拦截的时候 其中一个FilterChain匹配成功后 其他FilterChain不再起作用
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private MyUserDetailService userDetailService;
//
//    @Autowired
//    private NoEncryptPasswordEncoder passwordEncoder;
//
//    /**
//     * URL匹配 拦截过滤配置
//     * */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .requestMatchers().antMatchers("/api/**","/user/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**").authenticated()
//                .antMatchers("/user/**").permitAll()
//                .and()
////                .formLogin().loginPage("/login.html")
////                .successForwardUrl("/authLogin")
////                .successForwardUrl("登录成功跳转的URL")
////                .successHandler()
////                .failureHandler()
//                .httpBasic();
//    }
//
//
//
//    /**
//     * 用户名密码认证 测试
//     * */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        // 配置用户名密码，这里采用内存方式，生产环境需要从数据库获取
////        auth.inMemoryAuthentication()
////                .withUser("admin")
////                .password("123456")
////                .roles("USER");
////    }
//
//
//    /**
//     * 用户名密码认证 生产
//     * */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
////    }
//}
