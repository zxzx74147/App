package com.zxzx74147.dksq;

import android.app.Application;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 2017/1/11.
 */

public class DKApplication extends Application {

    private static DKApplication mInstance ;

    //use ZXApplicationDelegate.getApplication();
//    public static DKApplication sharedInstance(){
//        return mInstance;
//    }

    public void onCreate(){
        super.onCreate();
        ZXApplicationDelegate.onCreate(this);
        mInstance = this;
    }
}
