package com.zxzx74147.devlib;

import android.app.Application;

import com.lusfold.androidkeyvaluestore.KVStore;
import com.zxzx74147.devlib.image.ImageModelInterface;

/**
 * Created by zhengxin on 15/8/26.
 */
public class ZXApplicationDelegate {

    public static Application mApplication;

    public static Application getApplication() {
        return mApplication;
    }

    public static void onCreate(Application application) {
        mApplication = application;
        ImageModelInterface.init(mApplication);
        KVStore.init(mApplication, "zx_lib.db");
        KVStore.getInstance().setDebug(true);

    }

}
