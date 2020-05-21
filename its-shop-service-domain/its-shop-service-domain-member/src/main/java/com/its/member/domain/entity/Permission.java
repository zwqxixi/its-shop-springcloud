package com.its.member.domain.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "its_permission")
@Data
public class Permission {
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 方法类型
     */
    private String method;

    /**
     * 网关前缀
     */
    @Column(name = "zuul_prefix")
    private String zuulPrefix;

    /**
     * 服务前缀
     */
    @Column(name = "service_prefix")
    private String servicePrefix;

    /**
     * 请求路径
     */
    private String uri;

    /**
     * 创建日期
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 更新日期
     */
    @Column(name = "updateTime")
    private Date updatetime;

}