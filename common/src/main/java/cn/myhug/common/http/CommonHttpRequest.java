package cn.myhug.common.http;

import com.google.common.reflect.TypeToken;
import com.zxzx74147.devlib.http.ZXHttpRequest;

import cn.myhug.common.data.RspData;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class CommonHttpRequest<T> extends ZXHttpRequest {

    public CommonHttpRequest(Class mClass) {
        super(new TypeToken<RspData<T>>(){}.getType());
    }


}
