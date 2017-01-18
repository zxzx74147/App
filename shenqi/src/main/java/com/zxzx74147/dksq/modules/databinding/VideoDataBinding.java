package com.zxzx74147.dksq.modules.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.dksq.modules.data.ItemData;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by zhengxin on 2017/1/11.
 */

public class VideoDataBinding {
    @BindingAdapter("content_video")
    public static void setContentImage(JCVideoPlayerStandard videoView, ItemData item) {
        if (videoView == null||item==null) {
            return;
        }
        String url = item.video_pic;
        ItemData.ImageSizeData size =  item.image_size;
        videoView.setUp(item.video, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST);
        videoView.widthRatio = size.m.get(0);
        videoView.heightRatio = size.m.get(1);
        Context context = videoView.getContext();
        if (context == null) {
            context = ZXApplicationDelegate.getApplication();
        }
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(com.zxzx74147.devlib.R.drawable.ic_launcher)
                .into(videoView.thumbImageView);
    }
}
