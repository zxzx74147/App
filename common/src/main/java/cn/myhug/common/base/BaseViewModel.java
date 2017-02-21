package cn.myhug.common.base;

import android.content.Context;
import android.databinding.BaseObservable;

import com.zxzx74147.devlib.http.ZXHttpConfig;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.ZXUniqueIDGenerator;

import java.lang.reflect.Type;

import cn.myhug.common.http.CommonHttpRequest;

/**
 * Created by zhengxin on 2017/2/9.
 */

public abstract class BaseViewModel<T extends BaseModel> extends BaseObservable {
    protected T mModel;
    protected Context mContext;
    protected int mUniqueId = ZXUniqueIDGenerator.getUniqueID();

    public BaseViewModel(Context context) {
        mContext = context;
    }

    public void setModel(T model) {
        mModel = model;
    }

    public  void destory(){
        ZXHttpRequest.cancelWithTag(mUniqueId);
    };

    public <T> ZXHttpRequest<T> getRequest(Class<T> mClass) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(mClass);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueId);
        return request;
    }

    public <T> ZXHttpRequest<T> getRequest(Type type) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(type);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueId);
        return request;
    }

}
