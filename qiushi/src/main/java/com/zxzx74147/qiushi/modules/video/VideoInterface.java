package com.zxzx74147.qiushi.modules.video;

import android.content.Context;

import com.zxzx74147.devlib.data.IntentData;
import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.modules.IntentType;

/**
 * Created by zhengxin on 16/9/6.
 */

public class VideoInterface {
    public static void startVideoActivity(Context context, ItemData videoData){
        IntentData<ItemData> data = new IntentData<>();
        data.data = videoData;
        data.type = IntentType.INTENT_TYPE_VIDEO;
        ZXActivityJumpHelper.startActivity(context,VideoActivity.class,data);
    }
}
