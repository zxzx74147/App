package com.zxzx74147.devlib.storage;

import com.lusfold.androidkeyvaluestore.KVStore;
import com.zxzx74147.devlib.callback.ICommonCallback;
import com.zxzx74147.devlib.utils.ZXJsonUtil;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by zhengxin on 2017/2/16.
 */

public class ZXKVStore {

    public static<T> T getSync(final String key, final T defaultValue,Class<T> mClass) {
        String value = KVStore.getInstance().get(key);
        if (value == null) {
            return defaultValue;
        }
        if(mClass == String.class){
            return (T) value;
        }

        T ret = ZXJsonUtil.fromJsonString(value,mClass);
        return ret;
    }

    public static<T> void getAsync(final String key, final T defaultValue, final Class<T> mClass,final ICommonCallback<T> callback) {
        Task.callInBackground(new Callable<T>() {
            @Override
            public T call() throws Exception {
                String value = KVStore.getInstance().get(key);
                if (value == null) {
                    return defaultValue;
                }
                if(mClass == String.class){
                    return (T) value;
                }
                T ret = ZXJsonUtil.fromJsonString(value,mClass);
                return ret;

            }
        }).continueWith(new Continuation<T, Object>() {
            @Override
            public Object then(Task<T> task) throws Exception {
                if (callback != null) {
                    callback.callback(task.getResult());
                }
                return task.getResult();
            }
        }, Task.UI_THREAD_EXECUTOR);

    }

//    public static String getSync(final String key, final String defaultValue) {
//        String value = KVStore.getInstance().get(key);
//        if (value == null) {
//            value = defaultValue;
//        }
//        return value;
//    }
//
//    public static void getAsync(final String key, final String defaultValue, final ICommonCallback<String> callback) {
//        Task.callInBackground(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                String value = KVStore.getInstance().get(key);
//                if (value == null) {
//                    return defaultValue;
//                }
//            }
//        }).continueWith(new Continuation<String, Object>() {
//            @Override
//            public Object then(Task<String> task) throws Exception {
//                if (callback != null) {
//                    callback.callback(task.getResult());
//                }
//                return task.getResult();
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//
//    }

    public static void setAsync(final String key, final Object value) {
        Task.callInBackground(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if(value==null){
                    return KVStore.getInstance().delete(key);
                }
                String valueString = null;
                if(value instanceof String){
                    valueString = (String) value;
                }else{
                    valueString = ZXJsonUtil.toJsonString(value);
                }
                return (int) KVStore.getInstance().insertOrUpdate(key, valueString);
            }
        }).continueWith(new Continuation<Integer, Object>() {
            @Override
            public Object then(Task<Integer> task) throws Exception {
                return task.getResult();
            }
        }, Task.UI_THREAD_EXECUTOR);

    }

    public static int setSync(final String key, final Object value) {
        if(value==null){
            return KVStore.getInstance().delete(key);
        }
        String valueString = null;
        if(value instanceof String){
            valueString = (String) value;
        }else{
            valueString = ZXJsonUtil.toJsonString(value);
        }
        return (int) KVStore.getInstance().insertOrUpdate(key, valueString);
    }
}
