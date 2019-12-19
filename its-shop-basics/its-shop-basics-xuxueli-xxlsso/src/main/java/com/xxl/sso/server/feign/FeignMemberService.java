package com.xxl.sso.server.feign;

import com.its.member.api.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName: FeignMemberService
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/18 15:48
 * @Description: TODO
 */
@FeignClient("its-shop-member")
public interface FeignMemberService extends MemberLoginService {
}
