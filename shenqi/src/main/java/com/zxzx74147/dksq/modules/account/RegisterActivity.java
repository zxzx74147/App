package com.zxzx74147.dksq.modules.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.utils.ZXMd5Helper;
import com.zxzx74147.devlib.utils.ZXStringUtil;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityRegisterBinding;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.data.UserData;

import cn.myhug.common.base.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding mBinding = null;
    private ZXHttpRequest<UserData> mRequest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        mBinding.setHandler(this);
    }

    public void onRegister(View v){
        String username = mBinding.username.getText().toString();
        String password = mBinding.password.getText().toString();
        password = ZXMd5Helper.getMD5(password);
        if(!ZXStringUtil.checkString(username)||!ZXStringUtil.checkString(password)){
            showToast("fill params!");
        }
        mRequest = getRequest(UserData.class);
        mRequest.setUrl(Config.HOST+AccountHttpConfig.ADDRESS_REGISTER);
        mRequest.addParam("name",username);
        mRequest.addParam("password",password);
        mRequest.send(new ZXHttpCallback<UserData>() {
            @Override
            public void onResponse(ZXHttpResponse<UserData> response) {
                if(response.isSuccess()){
                    finish();
                }
            }
        });
    }


}

