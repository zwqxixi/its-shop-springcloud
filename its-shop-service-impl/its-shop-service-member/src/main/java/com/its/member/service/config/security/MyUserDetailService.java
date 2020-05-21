package com.its.member.service.config.security;

import com.its.member.domain.entity.Member;
import com.its.member.service.feign.FeignMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * @className: CustomUserDetailService
 * @author: wenqin.zhao
 * @createDate: 2020/5/11 20:07
 * @description: TODO
 */
@Service("userDetailService")
@Slf4j
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private FeignMemberService feignMemberService;

    /**
      * @description: 根据用户名查找用户信息  返回用户信息实体
      * @param:
      * @return: 用于身份认证的 UserDetails 用户信息实体
      * @author: wenqin.zhao
      * @createDate: 20:09 2020/5/11
      */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Member member = feignMemberService.findByUserName(userName);
        if (member == null) {
            log.info("用户不存在");
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
//        for (Role role : member.getRoles()) {
//            //角色必须是ROLE_开头，可以在数据库中设置
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
//            grantedAuthorities.add(grantedAuthority);
//            //获取权限
//            for (Permission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
//                grantedAuthorities.add(authority);
//            }
//        }
        User user = new User(member.getMemberName(), member.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
