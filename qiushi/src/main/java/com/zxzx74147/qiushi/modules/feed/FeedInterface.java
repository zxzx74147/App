package com.zxzx74147.qiushi.modules.feed;

import android.content.Context;

import com.zxzx74147.devlib.data.IntentData;
import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.TagConfig;
import com.zxzx74147.qiushi.modules.IntentType;
import com.zxzx74147.qiushi.modules.video.VideoActivity;

/**
 * Created by zhengxin on 16/9/6.
 */

public class FeedInterface {
    public static void startFeedActivity(Context context, TagConfig tag){
        IntentData<TagConfig> data = new IntentData<>();
        data.data = tag;
        data.type = IntentType.INTENT_TYPE_FEED;
        ZXActivityJumpHelper.startActivity(context,FeedActivity.class,data);
    }
}
