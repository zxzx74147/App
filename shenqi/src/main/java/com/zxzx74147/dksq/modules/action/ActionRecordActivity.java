package com.zxzx74147.dksq.modules.action;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityActionRecordBinding;
import com.zxzx74147.dksq.modules.action.viewmodel.ActionViewModel;
import com.zxzx74147.dksq.modules.model.ActionModel;

import java.util.List;

import cn.myhug.common.base.BaseActivity;
import cn.myhug.common.callback.IFileSelectCallback;
import cn.myhug.common.utils.ImageSelectHelper;

public class ActionRecordActivity extends BaseActivity {

    private ActivityActionRecordBinding mBinding = null;
    private ActionModel mData = new ActionModel();
    private ActionViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ActionViewModel(this);
        mData = new ActionModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_action_record);
        mBinding.setVm(mViewModel);
    }

    public void onImageClick(final View v) {
        ImageSelectHelper.selectImageSingle(this, new IFileSelectCallback() {
            @Override
            public void onFileSelect(List<String> files) {
                if(files!=null&& files.size()>0) {
                    mData.image.add(files.get(0));
                }
            }
        });
    }

    public void onSubmitClick(View v) {
//        ZXHttpRequest<ImageModel> request = getRequest(ImageModel.class);
//        request.setUrl(Config.HOST + UploadHttpConfig.ADDRESS_UPLOAD_IMAGE);
//        request.addParam("image", new File(mData.image));
//        request.send(new ZXHttpCallback<ImageModel>() {
//            @Override
//            public void onResponse(ZXHttpResponse<ImageModel> response) {
//                if (response.isSuccess()) {
//
//                    ZXToastUtil.showToast(response.mData.image_key);
//                    ZXHttpRequest<ActionModel> request = getRequest(ActionModel.class);
//                    request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_GET);
//                    request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_ADD);
//                    request.addParam("image", response.mData.image_key);
//                    request.addParam("comment", mBinding.comment.getText().toString());
//                    request.addParam("type", mData.type);
//                    request.send(new ZXHttpCallback<ActionModel>() {
//                        @Override
//                        public void onResponse(ZXHttpResponse<ActionModel> response) {
//
//                        }
//                    });
//
//                } else {
//                    ZXToastUtil.showToast(response.mError.errmsg);
//                }
//            }
//        });
    }


}
