package com.its.common.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 17:16
 * @Description: 用户登录来源
 */

public enum ClientEnum {
    ANDROID(1,"android"),
    IOS(2,"Apple"),
    PC(3,"PC"),
    H5(4,"H5");

    private Integer clientCode;
    private String  clientType;

    ClientEnum(Integer clientCode, String clientType) {
        this.clientCode = clientCode;
        this.clientType = clientType;
    }

    private static Map<Integer,String> map = new HashMap<>();
    static{
        map.put(1,"android");
        map.put(2,"Apple");
        map.put(3,"PC");
        map.put(4,"H5");
    }

    public static String getClientTypeByCode(Integer code){
        return map.get(code);
    }

    public static List<String> getAllClientType(){
        List<String> list = new ArrayList<>();
        list.add("android");
        list.add("Apple");
        list.add("PC");
        list.add("H5");
        return list;
    }
}
