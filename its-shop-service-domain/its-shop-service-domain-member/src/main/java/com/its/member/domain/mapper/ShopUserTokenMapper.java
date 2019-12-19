package com.its.member.domain.mapper;

import com.its.member.domain.entity.ShopUserToken;
import tk.mybatis.mapper.common.Mapper;

public interface ShopUserTokenMapper extends Mapper<ShopUserToken> {

    ShopUserToken selectByUserIdAndLoginType(Integer userId,String loginType);

}