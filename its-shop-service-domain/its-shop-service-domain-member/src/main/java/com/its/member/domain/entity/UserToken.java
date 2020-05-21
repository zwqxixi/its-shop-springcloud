package com.its.member.domain.entity;

import javax.persistence.*;

@Table(name = "its_user_token")
public class UserToken {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String token;

    @Column(name = "login_type")
    private String loginType;

    @Column(name = "device_infor")
    private String deviceInfor;

    @Column(name = "is_availability")
    private Integer isAvailability;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return login_type
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType == null ? null : loginType.trim();
    }

    /**
     * @return device_infor
     */
    public String getDeviceInfor() {
        return deviceInfor;
    }

    /**
     * @param deviceInfor
     */
    public void setDeviceInfor(String deviceInfor) {
        this.deviceInfor = deviceInfor == null ? null : deviceInfor.trim();
    }

    /**
     * @return is_availability
     */
    public Integer getIsAvailability() {
        return isAvailability;
    }

    /**
     * @param isAvailability
     */
    public void setIsAvailability(Integer isAvailability) {
        this.isAvailability = isAvailability;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}