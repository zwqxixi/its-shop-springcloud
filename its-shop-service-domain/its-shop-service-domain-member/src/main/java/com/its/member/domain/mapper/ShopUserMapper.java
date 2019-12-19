package com.its.member.domain.mapper;

import com.its.member.domain.entity.ShopUser;
import tk.mybatis.mapper.common.Mapper;

public interface ShopUserMapper extends Mapper<ShopUser> {

    ShopUser selectByMobileAndPassword(String mobile,String password);
}