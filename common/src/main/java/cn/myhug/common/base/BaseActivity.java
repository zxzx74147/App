package cn.myhug.common.base;

import android.app.Activity;
import android.content.Intent;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.devlib.http.ZXHttpConfig;
import com.zxzx74147.devlib.http.ZXHttpRequest;

import java.lang.reflect.Type;
import java.util.List;

import cn.myhug.common.http.CommonHttpRequest;
import cn.myhug.common.utils.ImageSelectHelper;
import cn.myhug.common.utils.RequestCode;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class BaseActivity extends ZXBaseActivity {


    public <T> ZXHttpRequest<T> getRequest(Class<T> mClass) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(mClass);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueID);
        return request;
    }

    public <T> ZXHttpRequest<T> getRequest(Type type) {
        CommonHttpRequest<T> request = new CommonHttpRequest<>(type);
        request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_POST);
        request.setTag(mUniqueID);
        return request;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int type = requestCode - requestCode % RequestCode.REQUEST_FRAGMENT;
        switch (type) {
            case RequestCode.REQUEST_SELECT_IMAGE:
                if(resultCode== Activity.RESULT_OK) {
                    List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    ImageSelectHelper.dealResult(requestCode, resultCode, path);
                }else {
                    ImageSelectHelper.dealResult(requestCode, resultCode, null);
                }
                break;
        }
    }
}
