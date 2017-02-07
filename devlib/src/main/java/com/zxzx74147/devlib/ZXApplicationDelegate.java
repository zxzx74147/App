package com.zxzx74147.devlib;

import android.app.Application;
import android.content.res.Resources;

import com.lusfold.androidkeyvaluestore.KVStore;
import com.zxzx74147.devlib.image.ImageModelInterface;
import com.zxzx74147.devlib.orm.OrmInterface;

/**
 * Created by zhengxin on 15/8/26.
 */
public class ZXApplicationDelegate {

    private static Application mApplication;

    public static Application getApplication() {
        return mApplication;
    }

    public static Resources getRecources(){
        return mApplication.getResources();
    }

    public static void onCreate(Application application) {
        mApplication = application;
        ImageModelInterface.init(mApplication);
        OrmInterface.init(mApplication);

        KVStore.init(mApplication, "zx_lib.db");
        KVStore.getInstance().setDebug(true);

    }

}
