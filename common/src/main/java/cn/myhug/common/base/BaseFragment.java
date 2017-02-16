package cn.myhug.common.base;

import com.zxzx74147.devlib.base.ZXBaseFragment;
import com.zxzx74147.devlib.http.ZXHttpConfig;
import com.zxzx74147.devlib.http.ZXHttpRequest;

import java.lang.reflect.Type;

import cn.myhug.common.http.CommonHttpRequest;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class BaseFragment extends ZXBaseFragment {

    public <T> ZXHttpRequest<T> getRequest(Class<T> mClass) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(mClass);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_GET);
        request.setTag(mUniqueID);
        return request;
    }

    public <T> ZXHttpRequest<T> getRequest(Type type) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(type);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueID);
        return request;
    }
}
