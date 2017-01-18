package com.zxzx74147.qiushi.common.http;

import android.content.Context;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.devlib.http.ZXHttpRequest;

/**
 * Created by zhengxin on 16/9/8.
 */

public class HttpRequestFactory
{
    public static <T> ZXHttpRequest<T> getRequest(Context context,Class mClass) {

        ZXHttpRequest<T> request = new ZXHttpRequest<T>(mClass);
        if(context instanceof ZXBaseActivity) {
            request.setTag(((ZXBaseActivity) context).mUniqueID);
        }
        return request;
    }
}
