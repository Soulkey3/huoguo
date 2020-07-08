package cn.jsut.huoguo.util;

/**
 * 数字处理工具类
 */
public class MathUtil {


    //获取几位随机数
    public static String getRandomStr(int weishu){
        String s="";
        for (int x=0; x<weishu;x++){
            s+=(int)(Math.random()*10);
        }
        return s;
    }
}
