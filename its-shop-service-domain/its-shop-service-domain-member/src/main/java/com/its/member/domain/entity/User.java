package com.its.member.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "its_user")
public class User {
    /**
     * user_id
     */
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "JDBC")
    private Integer userId;

    /**
     * 手机号
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 邮箱号
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 用户名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 性别  1男  2女
     */
    @Column(name = "SEX")
    private Boolean sex;

    /**
     * 年龄
     */
    @Column(name = "AGE")
    private Byte age;

    /**
     * 注册时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 是否可用 1正常  2冻结
     */
    @Column(name = "IS_AVALIBLE")
    private Boolean isAvalible;

    /**
     * 用户头像
     */
    @Column(name = "PIC_IMG")
    private String picImg;

    /**
     * QQ联合登陆id
     */
    @Column(name = "QQ_OPENID")
    private String qqOpenid;

    /**
     * 微信公众号关注id
     */
    @Column(name = "WX_OPENID")
    private String wxOpenid;

    /**
     * 获取user_id
     *
     * @return USER_ID - user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置user_id
     *
     * @param userId user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取手机号
     *
     * @return MOBILE - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取邮箱号
     *
     * @return EMAIL - 邮箱号
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱号
     *
     * @param email 邮箱号
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户名
     *
     * @return USER_NAME - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取性别  1男  2女
     *
     * @return SEX - 性别  1男  2女
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别  1男  2女
     *
     * @param sex 性别  1男  2女
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return AGE - 年龄
     */
    public Byte getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * 获取注册时间
     *
     * @return CREATE_TIME - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否可用 1正常  2冻结
     *
     * @return IS_AVALIBLE - 是否可用 1正常  2冻结
     */
    public Boolean getIsAvalible() {
        return isAvalible;
    }

    /**
     * 设置是否可用 1正常  2冻结
     *
     * @param isAvalible 是否可用 1正常  2冻结
     */
    public void setIsAvalible(Boolean isAvalible) {
        this.isAvalible = isAvalible;
    }

    /**
     * 获取用户头像
     *
     * @return PIC_IMG - 用户头像
     */
    public String getPicImg() {
        return picImg;
    }

    /**
     * 设置用户头像
     *
     * @param picImg 用户头像
     */
    public void setPicImg(String picImg) {
        this.picImg = picImg == null ? null : picImg.trim();
    }

    /**
     * 获取QQ联合登陆id
     *
     * @return QQ_OPENID - QQ联合登陆id
     */
    public String getQqOpenid() {
        return qqOpenid;
    }

    /**
     * 设置QQ联合登陆id
     *
     * @param qqOpenid QQ联合登陆id
     */
    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid == null ? null : qqOpenid.trim();
    }

    /**
     * 获取微信公众号关注id
     *
     * @return WX_OPENID - 微信公众号关注id
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信公众号关注id
     *
     * @param wxOpenid 微信公众号关注id
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }
}