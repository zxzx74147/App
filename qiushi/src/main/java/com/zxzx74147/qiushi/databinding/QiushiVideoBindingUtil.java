package com.zxzx74147.qiushi.databinding;

import android.databinding.BindingAdapter;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.zxzx74147.qiushi.common.data.CommentData;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.ItemDataHelper;
import com.zxzx74147.qiushi.modules.video.widget.VideoView;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiVideoBindingUtil {

    @BindingAdapter({"video_item_bind"})
    public static void bindVideo(VideoView videoView, ItemData item) {
        if(!ItemDataHelper.isVideo(item)){
            return;
        }
        videoView.setVideo(item);
    }
}
