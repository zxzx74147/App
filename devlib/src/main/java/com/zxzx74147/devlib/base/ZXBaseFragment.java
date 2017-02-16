package com.zxzx74147.devlib.base;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import com.zxzx74147.devlib.http.ZXHttpConfig;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.ZXUniqueIDGenerator;

import java.lang.reflect.Type;


/**
 * Created by zhengxin on 15/8/27.
 */
public abstract class ZXBaseFragment extends Fragment {
    public int mUniqueID = ZXUniqueIDGenerator.getUniqueID();
    private Handler mHandler= null;

    public ZXBaseFragment() {
        super();
    }


    public <T> ZXHttpRequest<T> getRequest(Class<T> mClass) {
        ZXHttpRequest<T> request = new ZXHttpRequest<>(mClass);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueID);
        return request;
    }

    public <T> ZXHttpRequest<T> getRequest(Type type) {
        ZXHttpRequest<T> request = new ZXHttpRequest<>(type);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueID);
        return request;
    }
    //post a runnable ,ignore when this activity destroy.
    public void postDelayed(Runnable runnable,int time){
        if(mHandler==null){
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.postDelayed(runnable,time);

    }

    public void removeCallbacks(Runnable runnable){
        if(mHandler==null){
            return;
        }
        mHandler.removeCallbacks(runnable);
    }



    public  void onReselect(){

    }
}
