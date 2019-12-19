package com.its.member.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 20:45
 * @Description:  Feign客户端复用
 */
@FeignClient("its-shop-weixin")
public interface FeignWeiXinService{

}
