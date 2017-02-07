package com.zxzx74147.devlib.orm;

import android.app.Application;

import com.litesuits.orm.LiteOrm;

//import com.activeandroid.ActiveAndroid;

/**
 * Created by zhengxin on 15/9/1.
 */
public class OrmInterface {
    private static LiteOrm liteOrm = null;

    public static void init(Application application){
//        ActiveAndroid.initialize(application);
        liteOrm = LiteOrm.newSingleInstance(application, "liteorm.db");
        liteOrm.setDebugged(true);
    }

    public static LiteOrm getOrm(){
        return liteOrm;
    }
}
