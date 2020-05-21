package com.its.common.core.constant;

import java.util.HashMap;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:42
 * @Description:
 */
public class ErrorCodeEnum {

    public final static Long SUCCESS = 0L; //成功
    public final static Long WRONG_PARAM = 1L; //请求参数不正确
    public final static Long USER_OR_PASS_WRONG = 2L; //用户名或密码错误
    public final static Long SYSTEM_ERROR = 3L; //系统级错误


    private static HashMap<Long,String> message = new HashMap<>();

    static {
        message.put(0L, "成功");
        message.put(1L, "请求参数不正确");
        message.put(2L, "用户名或密码错误");
        message.put(3L, "系统级错误");
        message.put(4L,  "注销失败");
        message.put(5L, "token无效");
    }

    public static String getMsgString(Long code) {
        return message.get(code);
    }
}
