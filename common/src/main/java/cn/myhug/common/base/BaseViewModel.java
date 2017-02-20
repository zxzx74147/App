package cn.myhug.common.base;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by zhengxin on 2017/2/9.
 */

public abstract class BaseViewModel<T extends BaseModel> extends BaseObservable {
    private T mModel;
    protected Context mContext;

    public BaseViewModel(Context context) {
        mContext = context;
    }

    public void setModel(T model) {
        mModel = model;
    }

}
