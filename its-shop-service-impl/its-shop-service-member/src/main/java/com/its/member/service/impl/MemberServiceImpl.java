package com.its.member.service.impl;

import com.its.member.api.service.MemberService;
import com.its.member.service.feign.FeignWeiXinService;
import com.its.member.domain.entity.Member;
import com.its.member.domain.mapper.MemberMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 20:44
 * @Description:
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private FeignWeiXinService feignWeiXinService;

    @Override
    public Principal user(Principal principal) {
        return principal;
    }

    @Override
    public String query() {
        return "具有query权限";
    }

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public Member findByUserName(String userName) {
        if(!StringUtils.isEmpty(userName)){
            Example example = new Example(Member.class);
            example.createCriteria().andEqualTo("memberName",userName);
            Member member = memberMapper.selectOneByExample(example);
            return member;
        }
        return null;
    }

    /*@Override
    public TestEntity MemberToWeiXin() {
        return feignWeiXinService.getTestEntity();
    }*/
}
