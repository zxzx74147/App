package com.zxzx74147.dksq.modules.action;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpConfig;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.utils.ZXToastUtil;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityActionRecordBinding;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.action.config.ActionHttpConfig;
import com.zxzx74147.dksq.modules.data.ActionData;
import com.zxzx74147.dksq.modules.data.ImageData;
import com.zxzx74147.dksq.modules.upload.UploadHttpConfig;

import java.io.File;
import java.util.List;
import java.util.Random;

import cn.myhug.common.base.BaseActivity;
import cn.myhug.common.callback.IFileSelectCallback;
import cn.myhug.common.utils.ImageSelectHelper;

public class ActionRecordActivity extends BaseActivity {

    private ActivityActionRecordBinding mBinding = null;
    private ActionData mData = new ActionData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new ActionData();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_action_record);
        mBinding.setHandler(this);
        mBinding.setData(mData);
        mBinding.ring.setBackgroundColor(Color.GRAY);
        mBinding.ring.setFrontColor(Color.BLUE, Color.RED);
        mBinding.ring.setStartAngle(-90);
        mBinding.ring.setAngle(360);
        mBinding.ring.setWidth(40);
    }

    public void onImageClick(final View v) {
        ImageSelectHelper.selectImageSingle(this, new IFileSelectCallback() {
            @Override
            public void onFileSelect(List<String> files) {
                if (v == mBinding.image1) {
                    mData.image = files.get(0);
                } else if (v == mBinding.image2) {
                    {
                        mData.image = files.get(0);
                    }


                }
                mBinding.setData(mData);
            }
        });
    }

    public void onSubmitClick(View v){
        ZXHttpRequest<ImageData> request = getRequest(ImageData.class);
        request.setUrl(Config.HOST+ UploadHttpConfig.ADDRESS_UPLOAD_IMAGE);
        request.addParam("image",new File(mData.image));
        request.send(new ZXHttpCallback<ImageData>() {
            @Override
            public void onResponse(ZXHttpResponse<ImageData> response) {
                if(response.isSuccess()){

                    ZXToastUtil.showToast(response.mData.image_key);
                    ZXHttpRequest<ActionData> request = getRequest(ActionData.class);
                    request.setMethod(ZXHttpConfig.HTTP_METHOD.HTTP_GET);
                    request.setUrl(Config.HOST+ ActionHttpConfig.ADDRESSS_ACTION_ADD);
                    request.addParam("image",response.mData.image_key);
                    request.addParam("comment",mBinding.comment.getText().toString());
                    request.addParam("color",new Random().nextInt(1000000));
                    request.send(new ZXHttpCallback<ActionData>() {
                        @Override
                        public void onResponse(ZXHttpResponse<ActionData> response) {

                        }
                    });

                }else{
                    ZXToastUtil.showToast(response.mError.errmsg);
                }
            }
        });
    }


}
