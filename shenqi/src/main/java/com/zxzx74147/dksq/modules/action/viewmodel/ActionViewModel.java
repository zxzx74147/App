package com.zxzx74147.dksq.modules.action.viewmodel;

import android.content.Context;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.utils.ZXToastUtil;
import com.zxzx74147.dksq.modules.action.ActionSubmitManager;
import com.zxzx74147.dksq.modules.action.service.ActionDataService;
import com.zxzx74147.dksq.modules.model.ActionModel;

import java.util.List;

import cn.myhug.common.base.BaseViewModel;
import cn.myhug.common.callback.IFileSelectCallback;

/**
 * Created by zhengxin on 2017/2/17.
 */

public class ActionViewModel extends BaseViewModel<ActionModel> {

    private ActionDataService mService = null;

    public ActionViewModel(Context context) {
        super(context);
        mModel = new ActionModel();
        mService = new ActionDataService(mContext);
    }

    @Override
    public void destory() {
        super.destory();
    }

    public IFileSelectCallback getFileSelectCallback() {
        return mFileSelectCallback;
    }

    public String getImage(int index) {
        if (index >= mModel.image.size()) {
            return null;
        }
        return mModel.image.get(index);
    }

    public int getImageCount() {
        return mModel.image.size();
    }

    private IFileSelectCallback mFileSelectCallback = new IFileSelectCallback() {

        @Override
        public void onFileSelect(List<String> files) {
            ZXToastUtil.showToast(files.get(0));
            mModel.local_image.add(files.get(0));
            mModel.notifyChange();
        }
    };

    public void submitAction() {
        ActionSubmitManager.sharedInstance().enqueue(mModel);
    }

    public void loadActionDetail(int id) {
        mService.loadActionModel(id, new ZXHttpCallback<ActionModel>() {
            @Override
            public void onResponse(ZXHttpResponse<ActionModel> response) {
                if (response.isSuccess()) {
                    mModel = response.mData;
                }
            }
        });
    }
}
