package com.zm.zmtools.common;

import java.util.UUID;

/**
 * UUID构造类
 * 通过该类即可在普通工具类里获取spring管理的bean
 * @author MengMeng （王孟豪）
 * Created date: 2018/8/6 
 * @version: 0.1
 * @since JDK 1.80_144
 */
public class UUIDUtils {

    /**
     * 创建32位的UUID
     * 构造UUID后去掉“-”
     * @author MengMeng （王孟豪）
     * @param
     * @return java.lang.String
     */
    public static String getUUID32(){
        UUID uuid = UUID.randomUUID();
        String uuidStr =  uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }
}
