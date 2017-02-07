package com.zxzx74147.devlib.utils;

/**
 * Created by zhengxin on 2017/1/19.
 */

public class ZXTimeUtil {
    public static long mDiffTime = 0;

    public static long currentTimeMillis(){
        long time = System.currentTimeMillis();
        time +=mDiffTime;
        return time;
    }

    //时间戳相差10s，需要校正
    public static void fixCurrentTime(long serverTime){
        long diff = serverTime-System.currentTimeMillis();
        if(Math.abs(diff)>10*1000){
            mDiffTime =diff;
        }
    }
}
