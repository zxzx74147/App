package cn.myhug.common.http;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.utils.ZXJsonUtil;

import org.json.JSONObject;

import java.lang.reflect.Type;

import cn.myhug.common.data.ErrorData;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class CommonHttpRequest<T> extends ZXHttpRequest<T> {


    public CommonHttpRequest(Class mClass) {
        super(mClass);
    }

    public CommonHttpRequest(Type mType) {
        super(mType);
    }

    @Override
    protected boolean dealResponse(String rspString, ZXHttpResponse<T> response) {
        try{
            JSONObject json = new JSONObject(rspString);
            ErrorData error = ZXJsonUtil.fromJsonString(json.optString("error"),ErrorData.class);
            response.mError.errno = error.error;
            response.mError.errmsg = error.errorStr;
            if(response.mError.errno == 200){
                return super.dealResponse(json.optString("data"),response);
            }
            return false;
        }catch (Exception e){
            response.mError.errno = -1;
            e.printStackTrace();
            return false;
        }
    }
}
