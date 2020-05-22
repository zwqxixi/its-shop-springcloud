package com.its.basics.auth.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @className: AuthrizationConfig
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 19:16
 * @description: 认证授权配置
 */
@Configuration
//表示认证授权服务器
@EnableAuthorizationServer
@Order(Integer.MIN_VALUE)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    /**
     * 访问安全配置 配置前来验证token的Client需要用的角色
     * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启/oauth/token支持client_id以及client_secret作登录认证
                .allowFormAuthenticationForClients()
                // 开启 /oauth/token验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中，默认方式是存储在内存中，如下所示，注释掉的代码即为内存中存储的方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.withClientDetails(clientDetails());
        //配置在内存中，也可以从数据库中获取
        clients.inMemory() // 使用in-memory存储
                // client_id
                .withClient("android")
                //client_secret
                .secret("android")
                //允许的授权范围
                .scopes("read")
                //允许的授权类型
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("browser")
                .authorizedGrantTypes("password","refresh_token")
                .scopes("read");
                // 认证成功重定向URL
                //.redirectUris("http://localhost:8882/login","http://localhost:8883/login")
                //自动认证
                //.autoApprove(true);
    }

    @Bean
    public ClientDetailsService clientDetails(){
        return new CustomClientDetailService(dataSource, redisTemplate);
    }

    /**
     * 访问端点配置
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
                 .userDetailsService(userDetailService)
                 .authenticationManager(authenticationManager)
                 .tokenServices(defaultTokenServices())
                 //认证异常翻译
                 .exceptionTranslator(customWebResponseExceptionTranslator);

    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     *
     * */
    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(false);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    @Bean
    public CustomRedisTokenStore redisTokenStore() {
        CustomRedisTokenStore redisTokenStore = new CustomRedisTokenStore(redisConnectionFactory);
        return redisTokenStore;
    }

}
