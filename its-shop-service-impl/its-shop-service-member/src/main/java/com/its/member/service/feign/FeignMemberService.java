package com.its.member.service.feign;

import com.its.member.api.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @className: FeignMemberService
 * @author: wenqin.zhao
 * @createDate: 2020/5/12 16:29
 * @description: TODO
 */
@FeignClient("its-shop-member")
public interface FeignMemberService extends MemberService {
}
