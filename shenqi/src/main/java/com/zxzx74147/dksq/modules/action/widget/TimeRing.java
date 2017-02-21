package com.zxzx74147.dksq.modules.action.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.zxzx74147.devlib.utils.ZXFontUtil;
import com.zxzx74147.devlib.utils.ZXTextUtil;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ViewTimeRingBinding;

/**
 * Created by zhengxin on 2017/2/21.
 */

public class TimeRing extends RelativeLayout {
    public static final int MODE_COUNT_DOWN = 1;
    public static final int MODE_SELECT = 2;
    private int mMode = MODE_SELECT;
    private ViewTimeRingBinding mBinding = null;
    private final int mTimeSelected = 15*60;

    public TimeRing(Context context) {
        this(context, null);
    }

    public TimeRing(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_time_ring, this, true);
        mBinding.time.setTypeface(ZXFontUtil.getTypeface("font/DS-DIGI.TTF"));
        setMode(MODE_SELECT);
    }

    public void setMode(int mode) {
        mMode = mode;
        switch (mMode) {
            case MODE_COUNT_DOWN:
                setOnTouchListener(null);
                break;
            case MODE_SELECT:
                setOnTouchListener(mTouchListener);
                break;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY));
        ZXTextUtil.mesureMaxSize(mBinding.time, originalWidth * 70 / 100);
    }

    private float y = 0;
    private OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (MODE_COUNT_DOWN == mMode) {
                        return false;
                    }
                    y = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float diff = event.getY()-y;
                    int temp = (int) (diff/20)*60;
                    showTime(mTimeSelected+temp);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:

                    break;

            }
            return true;
        }
    };

    public void showTime(int time){
        time=Math.max(0,time);
        String sb = String.format("%02d:%02d",time/60%60,time%60);
        if(time/3600>0){
            sb=time/3600+":"+sb;
        }
        mBinding.time.setText(sb);

    }

}
