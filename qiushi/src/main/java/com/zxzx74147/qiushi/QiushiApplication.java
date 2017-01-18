package com.zxzx74147.qiushi;

import android.app.Application;

import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.qiushi.common.http.HttpInterface;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ZXApplicationDelegate.onCreate(this);
        HttpInterface.init(this);
    }
}
