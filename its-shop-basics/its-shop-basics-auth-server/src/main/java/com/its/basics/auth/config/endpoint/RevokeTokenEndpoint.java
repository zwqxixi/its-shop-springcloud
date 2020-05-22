//package com.its.basics.auth.config.endpoint;
//
//import com.rdf.common.constant.CommonConstant;
//import com.rdf.common.core.ResultDO;
//import com.rdf.uc.auth.server.config.Oauth2Configuration;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
//import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * oath2 登出接口
// * @date 2018-02-07
// */
//@FrameworkEndpoint
//@Slf4j
//public class RevokeTokenEndpoint {
//
//    @Autowired
//    private ConsumerTokenServices consumerTokenServices;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private DefaultTokenServices defaultTokenServices;
//
//    @Autowired
//    private Oauth2Configuration oauth2Configuration;
//
//    @ResponseBody
//    @DeleteMapping(value = "/oauth/token")
//    public ResultDO<String> revokeToken(@RequestParam("access_token") String accessToken) {
//        ResultDO<String> result = new ResultDO();
//        OAuth2Authentication oAuth2Authentication = defaultTokenServices.loadAuthentication(accessToken);
//        User user = (User) oAuth2Authentication.getPrincipal();
//        try {
//            if (consumerTokenServices.revokeToken(accessToken)) {
//                // redis 不为空，并且不支持多点登录，删除redis信息
//                if (null != stringRedisTemplate && oauth2Configuration.getMutiLogin() == 0) {
//                    stringRedisTemplate.delete(CommonConstant.CURRENT_USER_INFO + user.getUsername());
//                }
//                result.setSuccess(true);
//                result.setErrorMessage("注销成功");
//            } else {
//                result.setSuccess(false);
//                result.setResultCode("201");
//                result.setErrorMessage("注销成功");
//            }
//        } catch (Exception ex) {
//            log.info("登出接口 异常 {}", ex);
//            result.setSuccess(true);
//            result.setErrorMessage("注销成功");
//        }
//        return result;
//    }
//}
