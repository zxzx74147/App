package com.zxzx74147.dksq.modules.action.viewmodel;

import android.content.Context;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.dksq.modules.action.service.ActionDataService;
import com.zxzx74147.dksq.modules.model.ActionListModel;

import cn.myhug.common.base.BaseViewModel;

/**
 * Created by zhengxin on 2017/2/17.
 */

public class ActionListViewModel extends BaseViewModel {

    private ActionListModel mModel = null;
    private ActionDataService mService = null;

    public ActionListViewModel(Context context) {
        super(context);
        mModel = new ActionListModel();
        mService = new ActionDataService(mContext);
    }

    public void loadActionDetail(String key) {
        mService.loadActionListModel(key, new ZXHttpCallback<ActionListModel>() {
            @Override
            public void onResponse(ZXHttpResponse<ActionListModel> response) {
                if (response.isSuccess()) {
                    mModel = response.mData;
                }
            }
        });
    }
}
