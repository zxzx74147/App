package com.zxzx74147.devlib.http;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.utils.ZXStringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXHttpClient {


    private final static String TAG = "ZXHttpClient";
    private static OkHttpClient mClient = null;
    private static ZXHttpHook mHook = null;

    public static void setHook(ZXHttpHook hook){
        mHook = hook;
    }

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS));
        builder.followRedirects(true);
        mClient = builder.build();
    }

    public static Call sendRequestAsync(ZXHttpRequest request) {
        if(mHook!=null){
            mHook.onSendRequest(request);
        }
        String url = request.getUrl();
        if (!ZXStringUtil.checkString(url)) {
            ZXLog.e(TAG, "url is null!!");
            return null;
        }
        Request.Builder builder = new Request.Builder();
        StringBuffer sb = new StringBuffer(40);
        Set<Map.Entry<String, String>> set = request.getParams().entrySet();
        for (HashMap.Entry<String, String> entry : set) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        Set<Map.Entry<String, String>> headers = request.getHeader().entrySet();
        for (HashMap.Entry<String, String> entry : headers) {
            builder.addHeader(entry.getKey(),entry.getValue());
        }

        switch (request.getMethod()) {
            case HTTP_GET:
                url = url + "?" + sb.toString();
                break;
            case HTTP_POST:
//                builder.post(RequestBody.create())
                break;
        }
        ZXLog.i(TAG,"url="+url);
        builder.url(url);
        builder.get();
        Request okHttpRequest = builder.build();
        Call call = mClient.newCall(okHttpRequest);
        return call;
    }
}
