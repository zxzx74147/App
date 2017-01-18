package com.zxzx74147.devlib.base;

public interface IBaseRectView<T> {
    void setParam(T data);
    void onPause();
    void onResume();
    void onDestroy();
}
