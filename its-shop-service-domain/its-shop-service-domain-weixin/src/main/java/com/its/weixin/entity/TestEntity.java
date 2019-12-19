package com.its.weixin.entity;

import lombok.Data;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 19:44
 * @Description: 项目构建测试类
 */
@Data
public class TestEntity {

    private String username;

    private String password;

    public TestEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
