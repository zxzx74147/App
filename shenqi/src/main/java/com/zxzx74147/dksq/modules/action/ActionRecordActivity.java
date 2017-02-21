package com.zxzx74147.dksq.modules.action;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ViewActionRecordBinding;
import com.zxzx74147.dksq.modules.action.viewmodel.ActionViewModel;
import com.zxzx74147.dksq.modules.model.ActionModel;

import cn.myhug.common.base.BaseActivity;

public class ActionRecordActivity extends BaseActivity {

    private ViewActionRecordBinding mBinding = null;
    private ActionModel mData = new ActionModel();
    private ActionViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ActionViewModel(this);
        mData = new ActionModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.view_action_record);
        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mData);
        mViewModel.setModel(mData);
        mData.setComment("1234");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mViewModel.destory();

    }

}
