package com.zxzx74147.devlib.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.zxzx74147.devlib.data.IntentData;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.CustomToast;
import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.devlib.utils.ZXUniqueIDGenerator;


/**
 * Created by zhengxin on 15/8/27.
 */
public abstract class ZXBaseActivity extends AppCompatActivity {
    public int mUniqueID = ZXUniqueIDGenerator.getUniqueID();
    private Handler mHandler= null;

    public ZXBaseActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZXHttpRequest.cancelWithTag(mUniqueID);
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    public void showToast(String content) {
        CustomToast.newInstance().showToast(content);
    }

    protected void showToast(int contentID) {
        CustomToast.newInstance().showToast(contentID);
    }


    protected void refresh() {

    }

    protected Object getParams() {
        IntentData intentData = (IntentData) getIntent().getSerializableExtra(ZXActivityJumpHelper.INTENT_DATA);
        if (intentData == null) {
            return null;
        }
        return intentData.data;

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


}
