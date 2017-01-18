package com.zxzx74147.qiushi.databinding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zxzx74147.devlib.image.widget.ZXImageView;
import com.zxzx74147.qiushi.common.data.ImageSizeData;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.ItemDataHelper;

import java.util.List;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiImageBindingUtil {

    @BindingAdapter({"img_item_content"})
    public static void img_item_content(ZXImageView imageView, ItemData item) {
        if(!ItemDataHelper.hasImage(item)){
            return;
        }
        ImageSizeData size = item.image_size;
        float ratio = (float)size.m.get(1)/size.m.get(0);
        imageView.setRatio(ratio);
    }
}
