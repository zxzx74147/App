package com.zxzx74147.dksq.modules.action;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityActionDetailBinding;

public class ActionDetailActivity extends ZXBaseActivity {

    private ActivityActionDetailBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_action_detail);
    }
}
