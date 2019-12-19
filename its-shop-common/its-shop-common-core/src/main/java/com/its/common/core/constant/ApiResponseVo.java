package com.its.common.core.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:39
 * @Description: 接口统一返回
 */
@Data
public class ApiResponseVo<T> implements Serializable {
    private Long code;

    private String message;

    private T data;

    public static ApiResponseVo genSuccess(Object data) {
        ApiResponseVo res = new ApiResponseVo();
        res.setCode(0L);
        res.setMessage(null);
        res.setData(data);
        return res;
    }

    public static ApiResponseVo genError(Long code) {
        ApiResponseVo res = new ApiResponseVo();
        res.setCode(code);
        res.setMessage(ErrorCodeEnum.getMsgString(code));
        res.setData(null);
        return res;
    }

    public static ApiResponseVo genCustom() {
        return genCustom(0L,"成功", null);
    }

    public static ApiResponseVo genCustom(Long code, String message, Object data) {
        ApiResponseVo res = new ApiResponseVo();
        res.setCode(code);
        res.setMessage("自定义消息:" + message);
        res.setData(data);
        return res;
    }
}
