package com.zxzx74147.dksq;

import android.app.Application;

/**
 * Created by zhengxin on 2017/1/11.
 */

public class DKApplication extends Application {

    private static DKApplication mInstance ;

    public static DKApplication sharedInstance(){
        return mInstance;
    }
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }
}
