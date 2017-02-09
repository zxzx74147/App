package com.zxzx74147.dksq.modules.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityLoginBinding;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends ZXBaseActivity  {

    private ActivityLoginBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
    }


}

