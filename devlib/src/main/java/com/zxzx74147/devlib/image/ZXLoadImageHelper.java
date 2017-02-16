package com.zxzx74147.devlib.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.devlib.image.widget.ZXImageView;
import com.zxzx74147.devlib.utils.ZXStringUtil;

/**
 * Created by zhengxin on 16/1/10.
 */
public class ZXLoadImageHelper {


    public static Bitmap loadImageSync(String path, int maxWidth) {
        Context context = ZXApplicationDelegate.getApplication();

        try {
            Bitmap bm = Glide.with(context).load(path).asBitmap().into(maxWidth, maxWidth).get();
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadImageView(String url, ImageView imageView) {

        if (!ZXStringUtil.checkString(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        if (imageView instanceof ZXImageView) {
            if (url.equals(((ZXImageView) imageView).getImageUrl())) {
                return;
            } else {
                imageView.setImageDrawable(null);
            }
        }
        Context context = imageView.getContext();
        if (context == null) {
            context = ZXApplicationDelegate.getApplication();
        }
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
