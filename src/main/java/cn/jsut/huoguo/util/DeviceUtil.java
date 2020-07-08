package cn.jsut.huoguo.util;

/**
 * 判断设备工具类
 */
public class DeviceUtil {



    public static boolean isMobile(String userAgent){
        if (userAgent.contains("Android")||userAgent.contains("iPhone")){
            return true;
        }
        return false;
    }
}
