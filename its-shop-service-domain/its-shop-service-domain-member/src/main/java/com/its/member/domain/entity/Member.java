package com.its.member.domain.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "its_member")
@Data
public class Member {
    /**
     * 会员id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 会员名
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别 1男0女
     */
    private Boolean sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 出生日
     */
    private Date birthday;

    /**
     * 注册日期
     */
    @Column(name = "createTime")
    private Date createtime;


    private List<Role> roles;

}