package com.zxzx74147.dksq.modules.action;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityActionRecordBinding;

public class ActionRecordActivity extends ZXBaseActivity {

    private ActivityActionRecordBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_action_record);
        mBinding.ring.setBackgroundColor(Color.GRAY);
        mBinding.ring.setFrontColor(Color.GREEN, Color.RED);
        mBinding.ring.setStartAngle(-90);
        mBinding.ring.setAngle(360);
        mBinding.ring.setWidth(40);
    }
}
