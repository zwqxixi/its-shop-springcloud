package com.its.member.domain.mapper;

import com.its.member.domain.entity.UserToken;
import tk.mybatis.mapper.common.Mapper;

public interface UserTokenMapper extends Mapper<UserToken> {

    UserToken selectByUserIdAndLoginType(Integer userId,String loginType);

}