package com.its.member.domain.mapper;

import com.its.member.domain.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    User selectByMobileAndPassword(String mobile,String password);
}