package com.zxzx74147.dksq.modules.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.devlib.image.widget.ZXImageView;
import com.zxzx74147.devlib.utils.ZXStringUtil;
import com.zxzx74147.dksq.modules.data.ItemData;

import java.util.List;

/**
 * Created by zhengxin on 2017/1/11.
 */

public class ImageDataBinding {
    @BindingAdapter("content_image")
    public static void setContentImage(ImageView imageView, ItemData item) {
        if (imageView == null||item==null) {
            return;
        }

        String url = item.image_high;
        ItemData.ImageSizeData size =  item.image_size;
        if (imageView instanceof ZXImageView) {
            if (url != null && url.equals(((ZXImageView) imageView).getImageUrl())) {
                return;
            } else {
                imageView.setImageDrawable(null);
            }
            if(size!=null){
                List<Integer> sizem = item.image_size.m;
                ((ZXImageView) imageView).setRatio((float)sizem.get(1)/sizem.get(0));
            }
        }
        ((ZXImageView) imageView).setImageUrl(url);
        if (!ZXStringUtil.checkString(url)) {
            return;
        }
        Context context = imageView.getContext();
        if (context == null) {
            context = ZXApplicationDelegate.getApplication();
        }
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(com.zxzx74147.devlib.R.drawable.ic_launcher)
                .into(imageView);
    }
}
