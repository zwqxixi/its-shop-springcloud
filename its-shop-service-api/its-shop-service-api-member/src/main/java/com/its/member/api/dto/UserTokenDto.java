package com.its.member.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 16:13
 * @Description:
 */
@Data
@ApiModel(value = "用户唯一登录参数")
public class UserTokenDto implements Serializable {

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 登陆类型 PC、Android 、IOS
     */
    @ApiModelProperty(value = "登陆类型")
    private Integer type;
    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private String deviceInfor;
    // 为什么一个接口单独定义一个 dto请求参数类 swagger 接口文档对称
}
