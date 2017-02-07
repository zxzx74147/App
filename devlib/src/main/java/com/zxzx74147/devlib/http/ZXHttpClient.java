package com.zxzx74147.devlib.http;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.utils.ZXStringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXHttpClient {


    private final static String TAG = "ZXHttpClient";
    private static OkHttpClient mClient = null;
    private static ZXHttpHook mHook = null;

    public static void setHook(ZXHttpHook hook) {
        mHook = hook;
    }

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS));
        builder.followRedirects(true);
        mClient = builder.build();
    }

    public static Call sendRequestAsync(ZXHttpRequest request) {
        if (mHook != null) {
            mHook.onSendRequest(request);
        }
        String url = request.getUrl();
        if (!ZXStringUtil.checkString(url)) {
            ZXLog.e(TAG, "url is null!!");
            return null;
        }
        Request.Builder builder = new Request.Builder();


        Set<Map.Entry<String, String>> headers = request.getHeader().entrySet();
        for (HashMap.Entry<String, String> entry : headers) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }

        Set<Map.Entry<String, String>> set = request.getParams().entrySet();
        Set<Map.Entry<String, File>> files = request.getFile().entrySet();
        switch (request.getMethod()) {
            case HTTP_GET:
                StringBuffer sb = new StringBuffer(set.size() * 10);
                for (HashMap.Entry<String, String> entry : set) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                }
                url = url + "?" + sb.toString();
                break;
            case HTTP_POST:
                MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();
                boolean hasBody = false;
                for (HashMap.Entry<String, String> entry : set) {
                    requestBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                    hasBody= true;
                }
                for (HashMap.Entry<String, File> entry : files) {
                    File file = entry.getValue();
                    if(!file.exists()){
                        ZXLog.e(TAG,"file don't exist:"+file.toString());
                        continue;
                    }
                    requestBodyBuilder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(null, file));
                    hasBody= true;
                }
                if(hasBody) {
                    builder.post(requestBodyBuilder.build());
                }
                break;
        }
        ZXLog.i(TAG, "url=" + url);
        builder.url(url);
        builder.get();
        Request okHttpRequest = builder.build();
        Call call = mClient.newCall(okHttpRequest);
        return call;
    }
}
