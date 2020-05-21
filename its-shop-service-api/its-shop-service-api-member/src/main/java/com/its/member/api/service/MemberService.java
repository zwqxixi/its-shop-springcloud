package com.its.member.api.service;

import com.its.member.domain.entity.Member;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * @className: MemberService
 * @author: wenqin.zhao
 * @createDate: 2020/5/12 17:31
 * @description: TODO
 */
@RequestMapping("api")
public interface MemberService {

    @GetMapping("/current")
    Principal user(Principal principal);

    @GetMapping("/getMemberInfo")
    Member findByUserName(@RequestParam("userName") String userName);

    @GetMapping("query")
    @PreAuthorize("hasAnyAuthority('query')")
    String query();

    @GetMapping("hello")
    @PreAuthorize("hasAnyAuthority('hello')")
    String hello();
}
