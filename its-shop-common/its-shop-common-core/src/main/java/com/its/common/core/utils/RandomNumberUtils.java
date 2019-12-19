package com.its.common.core.utils;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/9 10:11
 * @Description: 生成随机数Utils
 */
public class RandomNumberUtils {
    //随机生成6为验证码
    public static String getRandomNumber6(){
        String code = "";
        for(int i=0;i<6;i++){
            int random = (int)(Math.random()*10);
            code += String.valueOf(random);
        }
        return code;
    }
}
