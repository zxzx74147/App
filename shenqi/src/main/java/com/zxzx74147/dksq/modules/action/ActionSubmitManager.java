package com.zxzx74147.dksq.modules.action;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.utils.ZXJsonUtil;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.action.config.ActionHttpConfig;
import com.zxzx74147.dksq.modules.model.ActionModel;
import com.zxzx74147.dksq.modules.upload.ImageUploadHelper;

import java.util.LinkedList;
import java.util.List;

import cn.myhug.common.callback.IFileUploadCallback;
import cn.myhug.common.http.CommonHttpRequest;

/**
 * Created by zhengxin on 2017/2/21.
 */

public class ActionSubmitManager {
    private static ActionSubmitManager mInstance = new ActionSubmitManager();

    private LinkedList<ActionModel> mQueue = new LinkedList<>();

    public static ActionSubmitManager sharedInstance() {
        return mInstance;
    }

    private boolean mLock = false;

    public void enqueue(ActionModel actionModel) {
        mQueue.add(actionModel);
        checkSubmit();
    }

    private void checkSubmit() {
        if (mQueue.size() == 0 || mLock) {
            return;
        }
        final ActionModel actionModel = mQueue.pop();
        mLock = true;
        if (actionModel.local_image.size() == 0) {
            submitAction(actionModel);
        } else {
            ImageUploadHelper.uploadImage(actionModel.local_image, new IFileUploadCallback() {
                @Override
                public void onFileUpload(boolean success, List<String> result) {
                    if (success) {
                        actionModel.image = result;
                        submitAction(actionModel);
                    } else {

                    }
                }
            });
        }
    }

    private void submitAction(ActionModel actionModel) {
        ZXHttpRequest<ActionModel> request = new CommonHttpRequest<>(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_ADD);
        request.addParam("image", ZXJsonUtil.toJsonString(actionModel.image));
        request.addParam("comment", actionModel.comment);
        request.addParam("type", actionModel.type);
        request.send(new ZXHttpCallback<ActionModel>() {
            @Override
            public void onResponse(ZXHttpResponse<ActionModel> response) {
                if (response.isSuccess()) {
                    mLock = false;
                    checkSubmit();
                }
            }
        });
    }


}
