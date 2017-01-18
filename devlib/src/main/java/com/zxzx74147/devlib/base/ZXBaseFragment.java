package com.zxzx74147.devlib.base;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.ZXUniqueIDGenerator;


/**
 * Created by zhengxin on 15/8/27.
 */
public abstract class ZXBaseFragment extends Fragment {
    public int mUniqueID = ZXUniqueIDGenerator.getUniqueID();
    private Handler mHandler= null;

    public ZXBaseFragment() {
        super();
    }


    public <T> ZXHttpRequest<T> getRequest(Class mClass) {
        ZXHttpRequest<T> request = new ZXHttpRequest<T>(mClass);
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



    public abstract void onReselect();
}
