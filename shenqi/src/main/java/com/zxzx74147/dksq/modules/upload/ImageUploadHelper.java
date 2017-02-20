package com.zxzx74147.dksq.modules.upload;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.image.ZXImageHelper;
import com.zxzx74147.devlib.utils.ZXFileUtil;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.model.ImageModel;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Task;
import cn.myhug.common.callback.IFileUploadCallback;
import cn.myhug.common.http.CommonHttpRequest;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class ImageUploadHelper {
    public static void uploadImage(final List<String> mImages, final IFileUploadCallback callback) {
        if (mImages == null) {
            return;
        }

        Task.callInBackground(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> image_keys = new LinkedList<>();
                for (String image : mImages) {
                    Bitmap bm = Glide.with(ZXApplicationDelegate.getApplication()).
                            load(image).asBitmap().into(600, 600).get();
                    File file = ZXImageHelper.saveImage(bm, System.currentTimeMillis() + ".jpg");
                    ZXHttpRequest<ImageModel> request = new CommonHttpRequest<>(ImageModel.class);
                    request.setUrl(Config.HOST + UploadHttpConfig.ADDRESS_UPLOAD_IMAGE);
                    request.addParam("image", file);
                    ZXHttpResponse<ImageModel> response = request.sendSync();
                    if (!response.isSuccess()) {
                        return null;
                    }
                    ZXFileUtil.deleteFile(file);
                    image_keys.add(response.mData.image_key);
                }
                return image_keys;
            }
        }).continueWith(new bolts.Continuation<List<String>, Object>() {
            @Override
            public Object then(Task<List<String>> task) throws Exception {
                if (callback == null) {
                    return false;
                }
                if (task.getResult() == null) {
                    callback.onFileUpload(true, task.getResult());
                }
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);


    }
}
