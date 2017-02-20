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
import com.zxzx74147.dksq.databinding.ActivityLoginBinding;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.model.UserModel;

import cn.myhug.common.base.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding mBinding = null;
    private ZXHttpRequest<UserModel> mRequest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
    }

    public void onLogin(View v){
        String username = mBinding.username.getText().toString();
        String password = mBinding.password.getText().toString();
        password = ZXMd5Helper.getMD5(password);
        if(!ZXStringUtil.checkString(username)||!ZXStringUtil.checkString(password)){
            showToast("fill params!");
        }
        mRequest = getRequest(UserModel.class);
        mRequest.setUrl(Config.HOST+AccountHttpConfig.ADDRESS_LOGIN);
        mRequest.addParam("name",username);
        mRequest.addParam("password",password);
        mRequest.send(new ZXHttpCallback<UserModel>() {
            @Override
            public void onResponse(ZXHttpResponse<UserModel> response) {

                if(response.isSuccess()){
                    AccountManager.sharedInstance().saveUser(response.mData);
                    finish();
                }

            }
        });
    }


}

