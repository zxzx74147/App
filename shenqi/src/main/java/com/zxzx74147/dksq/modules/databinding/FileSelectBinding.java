package com.zxzx74147.dksq.modules.databinding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.view.View;

import java.util.List;

import cn.myhug.common.callback.IFileSelectCallback;
import cn.myhug.common.utils.ImageSelectHelper;

/**
 * Created by zhengxin on 2017/1/11.
 */

public class FileSelectBinding {



    @BindingAdapter("onImageSelect")
    public static void setOnImageSelect(final View imageView, final IFileSelectCallback item) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelectHelper.selectImageSingle(((Activity) imageView.getContext()), new IFileSelectCallback() {
                    @Override
                    public void onFileSelect(List<String> files) {
                        imageView.setTag(cn.myhug.common.R.id.tag_file);
                        if(item!=null) {
                            item.onFileSelect(files);
                        }
                    }
                });
            }
        });

    }
}
