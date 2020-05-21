package com.its.member.api.service;

import com.its.common.core.constant.ApiResponseVo;
import com.its.member.api.dto.UserTokenDto;
import com.its.member.api.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:26
 * @Description: 用户登录接口
 */
@Api(tags="用户登录服务接口") //@Api()用于这个类  表示这个类是Swagger的资源  最终会被网关获取
@RequestMapping("/user")
public interface MemberLoginService{

    @PostMapping("/onlyLogin")
    @ApiOperation(value = "用户登录接口")
    ApiResponseVo onlyLogin(UserTokenDto userTokenDto) throws Exception;

    @GetMapping("/authLogin")
    @ApiOperation(value = "用户登录接口")
    ApiResponseVo<UserVo> authLogin(@RequestParam("mobile") String mobile, @RequestParam("password") String password);

}
