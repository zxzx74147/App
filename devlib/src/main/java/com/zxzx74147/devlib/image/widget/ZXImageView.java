package com.zxzx74147.devlib.image.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zxzx74147.devlib.R;

/**
 * Created by zhengxin on 15/8/27.
 */
public class ZXImageView extends RoundedImageView {

    private float mRatio = 0;
    private String mUrl = null;

    public ZXImageView(Context context) {
        super(context);
    }

    public ZXImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ZXImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ZXImageView);
        float radio = a.getFloat(R.styleable.ZXImageView_image_radio, 0);
        setRatio(radio);
        a.recycle();
    }
    public void setImageUrl(String url) {
        mUrl = url;
    }

    public void setRatio(float ratio){
        mRatio = ratio;
        requestLayout();
    }

    public String getImageUrl() {
        return mUrl;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mRatio==0){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * mRatio);
        setMeasuredDimension(width, height);
    }

}
