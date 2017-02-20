package cn.myhug.common.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.ZXUniqueIDGenerator;

import java.lang.reflect.Type;

import cn.myhug.common.http.CommonHttpRequest;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class BaseDataService {
    private int mUniqueID = ZXUniqueIDGenerator.getUniqueID();
    protected Handler mHandler;
    protected Context mContext;

    public BaseDataService(Context context) {
        mContext = context;
        mHandler = new Handler(Looper.getMainLooper());
    }

    protected  <T> ZXHttpRequest<T> getRequest(Class<T> mClass) {
        ZXHttpRequest<T> request = new CommonHttpRequest<T>(mClass);
        request.setTag(mUniqueID);
        return request;
    }

    protected <T> ZXHttpRequest<T> getRequest(Type mType) {

        ZXHttpRequest<T> request = new CommonHttpRequest<T>(mType);
        request.setTag(mUniqueID);
        return request;
    }

    public void shutdown() {
        ZXHttpRequest.cancelWithTag(mUniqueID);
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
