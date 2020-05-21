package com.its.member.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDto
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/18 19:41
 * @Description: TODO
 */
@Data
public class UserVo implements Serializable{

    private Integer userId;

    private String mobile;

    private String email;

    private String password;

    private String userName;

    private Boolean sex;

    private Byte age;

    private Date createTime;

    private Boolean isAvalible;

    private String picImg;

    private String qqOpenid;

    private String wxOpenid;
}
