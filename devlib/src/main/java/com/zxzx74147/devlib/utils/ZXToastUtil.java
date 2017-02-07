package com.zxzx74147.devlib.utils;

import android.widget.Toast;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 16/1/10.
 */
public class ZXToastUtil {

    public static void showToast(int id){
        showToast(ZXApplicationDelegate.getRecources().getString(id));
    }

    public static void showToast(String toast){
        Toast.makeText(ZXApplicationDelegate.getApplication(),toast,Toast.LENGTH_LONG);
    }
}
