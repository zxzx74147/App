package com.zxzx74147.dksq.modules.upload;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zhengxin on 2017/2/7.
 */

public class UploadHelper {
    public static String uploadImage(String filePath){
        //创建RequestBody
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        File file = new File(filePath);
        builder.addFormDataPart("image", file.getName(), RequestBody.create(null, file));
        return null;

    }
}
