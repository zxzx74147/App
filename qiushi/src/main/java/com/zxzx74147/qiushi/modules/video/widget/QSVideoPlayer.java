package com.zxzx74147.qiushi.modules.video.widget;

import android.content.Context;
import android.util.AttributeSet;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by zhengxin on 16/9/8.
 */

public class QSVideoPlayer extends JCVideoPlayer {

    public QSVideoPlayer(Context context) {
        super(context);
    }

    public QSVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
