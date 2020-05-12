package com.its.basics.auth.config.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * @className: ResourceServerConfig
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 23:29
 * @description: 资源认证配置
 */
@Configuration
@EnableResourceServer //表示资源认证服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
        configurer
                .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint());
    }


}
