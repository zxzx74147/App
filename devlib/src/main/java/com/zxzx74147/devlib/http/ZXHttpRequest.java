package com.zxzx74147.devlib.http;

import android.util.SparseArray;

import com.zxzx74147.devlib.utils.ZXJsonUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.zxzx74147.devlib.http.ZXHttpConfig.HTTP_ERROR;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXHttpRequest<T> {
    private static SparseArray<List<ZXHttpRequest>> mRequests = new SparseArray<>();

    private ZXHttpConfig.HTTP_METHOD mMethod = ZXHttpConfig.HTTP_METHOD.HTTP_GET;

    private HashMap<String, String> mParams = new HashMap<>(10);
    private HashMap<String, File> mFile = new HashMap<>(1);

    private String mUrl = null;

    private String mContentType = null;

    private Class<T> mClass = null;
    private Type mType = null;

    private Call mCall = null;

    private HashMap<String, String> mHeader = new HashMap<>(5);

    private ZXHttpCallback<T> mCallback = null;

    private int mTag = 0;

    private ZXHttpRequest() {
    }

    private int getTag() {
        return mTag;
    }

    public void setTag(int tag) {
        mTag = tag;
    }

    public ZXHttpRequest(Class<T> mClass) {
        this.mClass = mClass;
    }

    public ZXHttpRequest(Type type) {
        mType = type;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setMethod(ZXHttpConfig.HTTP_METHOD method) {
        mMethod = method;
    }

    public void addParam(String key, String value) {
        mParams.put(key, value);
    }

    public void addParam(String key, File value) {
        mMethod = ZXHttpConfig.HTTP_METHOD.HTTP_POST;
        mFile.put(key, value);
    }

    public void addParam(String key, Object value) {
        mParams.put(key, value.toString());
    }

    public void addHeader(String key, String value) {
        mHeader.put(key, value);
    }

    public ZXHttpConfig.HTTP_METHOD getMethod() {
        return mMethod;
    }

    public String getUrl() {
        return mUrl;
    }

    public HashMap<String, String> getParams() {
        return mParams;
    }

    public ZXHttpResponse<T> sendSync(){
        mCall = ZXHttpClient.sendRequest(this);
        final ZXHttpResponse<T> response = new ZXHttpResponse<>();
        response.setRequest(ZXHttpRequest.this);

        try {
            Response rsp = mCall.execute();
            String rspString = rsp.body().string();
            dealResponse(rspString, response);

        } catch (IOException e) {
            e.printStackTrace();
            response.mError.errno = HTTP_ERROR;
            response.mError.errmsg = e.getMessage();

        }
        return response;
    }

    public void send(ZXHttpCallback<T> callback) {
        mCallback = callback;
        mCall = ZXHttpClient.sendRequest(this);

        if (mTag != 0) {
            List<ZXHttpRequest> list = mRequests.get(mTag);
            if (list == null) {
                list = new LinkedList<>();
                mRequests.put(mTag, list);
            }
            list.add(this);
        }
        final List<ZXHttpRequest> mList = mRequests.get(mTag);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mList != null) {
                    mList.remove(ZXHttpRequest.this);
                }
                if (call.isCanceled()) {
                    return;
                }
                final ZXHttpResponse<T> response = new ZXHttpResponse<>();
                response.setRequest(ZXHttpRequest.this);
                response.mError.errno = HTTP_ERROR;
                response.mError.errmsg = e.getMessage();
                Task.call(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        if (mCallback != null) {
                            mCallback.onResponse(response);
                        }
                        return null;
                    }
                }, Task.UI_THREAD_EXECUTOR);

            }

            @Override
            public void onResponse(Call call, final Response rsp) throws IOException {
                if (mList != null) {
                    mList.remove(ZXHttpRequest.this);
                }
                if (call.isCanceled() || mCallback == null) {
                    return;
                }
                final ZXHttpResponse<T> response = new ZXHttpResponse<>();
                response.setRequest(ZXHttpRequest.this);

                Task.callInBackground(new Callable<ZXHttpResponse<T>>() {
                    @Override
                    public ZXHttpResponse<T> call() throws Exception {
                        String rspString = rsp.body().string();
                        dealResponse(rspString, response);
                        return response;
                    }
                }).continueWith(new Continuation<ZXHttpResponse<T>, Object>() {
                    @Override
                    public Object then(Task<ZXHttpResponse<T>> task) throws Exception {
                        if (mCallback != null) {
                            mCallback.onResponse(response);
                        }
                        return null;
                    }
                }, Task.UI_THREAD_EXECUTOR);

            }
        });
    }

    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
        mCallback = null;
    }

    protected boolean dealResponse(String rspString, ZXHttpResponse<T> response) {
        T data = null;
        if (mClass != null) {
            data = ZXJsonUtil.fromJsonString(rspString, mClass);
        } else if (mType != null) {
            data = ZXJsonUtil.fromJsonString(rspString, mType);
        }
        response.mError.errno = 200;
        response.mData = data;
        return true;
    }

    public static void cancelWithTag(int tag) {
        List<ZXHttpRequest> list = mRequests.get(tag);
        if (list == null) {
            return;
        }
        for (ZXHttpRequest request : list) {
            request.cancel();
        }
        list.clear();
        mRequests.remove(tag);
    }

    HashMap<String, String> getHeader() {
        return mHeader;
    }

    HashMap<String, File> getFile() {
        return mFile;
    }
}
