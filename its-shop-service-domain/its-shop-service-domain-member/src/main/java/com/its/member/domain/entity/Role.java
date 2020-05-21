package com.its.member.domain.entity;

import com.its.member.domain.entity.Permission;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "its_role")
@Data
public class Role {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否有效 1是 0否
     */
    private Boolean valid;

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


    private List<Permission> permissions;

}