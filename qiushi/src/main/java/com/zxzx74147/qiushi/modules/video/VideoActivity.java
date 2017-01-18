package com.zxzx74147.qiushi.modules.video;

import android.databinding.DataBindingUtil;
import android.media.JetPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.qiushi.R;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.databinding.ActivityVideoBinding;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class VideoActivity extends ZXBaseActivity {
    private ActivityVideoBinding  mBinding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_video);
        ItemData data = (ItemData) getParams();
        mBinding.player.setUp(data.high_url, JCVideoPlayer.SCREEN_LAYOUT_DETAIL,"");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }
}
