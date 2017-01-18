package com.zxzx74147.qiushi.modules.video.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.qiushi.R;
import com.zxzx74147.qiushi.common.data.ImageSizeData;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.ItemDataHelper;
import com.zxzx74147.qiushi.databinding.WidgetVideoBinding;
import com.zxzx74147.qiushi.modules.video.VideoActivity;
import com.zxzx74147.qiushi.modules.video.VideoInterface;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by zhengxin on 16/9/7.
 */

public class VideoView extends FrameLayout {

    private WidgetVideoBinding mBinding = null;
    private float mRatio = 0;

    public VideoView(Context context) {
        super(context);
        init();
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.widget_video, this, true);
        mBinding.setHandler(this);
    }

    public void setVideo(ItemData data) {
        if(!ItemDataHelper.isVideo(data)){
            setVisibility(View.GONE);
            return;
        }
        mBinding.setItem(data);
        mBinding.player.setUp(data.high_url, JCVideoPlayer.SCREEN_LAYOUT_DETAIL,"");
        mBinding.player.addTextureView();
        mBinding.thumb.setVisibility(GONE);
        ImageSizeData size = data.image_size;
        float ratio = (float)size.m.get(1)/size.m.get(0);
        setRatio(ratio);
    }

    public void setRatio(float ratio){
        if(mRatio==ratio){
            return;
        }
        mRatio = ratio;
        mBinding.thumb.setRatio(ratio);
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * mRatio);
        setMeasuredDimension(width, height);
    }

    public void onVideoClick(View view){
//        mBinding.player.addTextureView();
//        JCVideoPlayerStandard.startFullscreen(getContext(), JCVideoPlayerStandard.class, mBinding.getItem().high_url, "11");
//        VideoInterface.startVideoActivity(getContext(),mBinding.getItem());
    }


}
