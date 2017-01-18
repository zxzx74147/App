package com.zxzx74147.qiushi.common.http;

import android.content.Context;

import com.zxzx74147.devlib.http.ZXHttpClient;

/**
 * Created by zhengxin on 16/9/7.
 */

public class HttpInterface {
    public static void init(Context context) {
        ZXHttpClient.setHook(new QiushiHttpHook());
    }
}
