package com.zxzx74147.dksq.modules.wiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhengxin on 2017/1/23.
 */

public class RingWidget extends View {

    private Paint mPaintFr = null;
    private Paint mPaintBg = null;

    private float mAngle = 0;
    private RectF mRect = new RectF();
    private float strokeWidth = 0;
    private int mFrontStartColor = 0;
    private int mFrontEndColor = 0;
    private int mBackgroundStartColor = 0;
    private int mBackgroundEndColor = 0;

    public RingWidget(Context context) {
        super(context);
        init();
    }

    public RingWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RingWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY));
    }

    private void init() {
        mPaintFr = new Paint();
        mPaintBg = new Paint();
        mPaintFr.setAntiAlias(true);
        mPaintBg.setAntiAlias(true);
        mPaintFr.setStyle(Paint.Style.STROKE);
        mPaintBg.setStyle(Paint.Style.STROKE);
    }

    public void setFrontShader(Shader gradient) {
        mPaintFr.setShader(gradient);
        postInvalidate();
    }

    public void setBackgroundShader(Shader gradient) {
        mPaintBg.setShader(gradient);
        postInvalidate();
    }

    public void setFrontColor(int color) {
        mPaintFr.setColor(color);
        postInvalidate();
    }

    public void setFrontColor(int startColor, int endColor) {
        mFrontStartColor = startColor;
        mFrontEndColor = endColor;
        postInvalidate();
    }

    public void setBackgroundColor(int color) {
        mPaintBg.setColor(color);
        postInvalidate();
    }

    public void setBackgroundColor(int startColor, int endColor) {
        mBackgroundStartColor = startColor;
        mBackgroundEndColor = endColor;
        postInvalidate();
    }

    public void setWidth(float width) {
        mPaintFr.setStrokeWidth(width);
        mPaintBg.setStrokeWidth(width);
        strokeWidth = width;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        mRect.set(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2);
        if(mFrontStartColor!=0||mFrontEndColor!=0){
            SweepGradient gradient = new SweepGradient(width/2, height/2, mFrontStartColor, mFrontEndColor);
            mPaintFr.setShader(gradient);
            mFrontStartColor = 0;
            mFrontEndColor =0;
        }

        if(mBackgroundStartColor!=0||mBackgroundEndColor!=0){
            SweepGradient gradient = new SweepGradient(width/2, height/2, mBackgroundStartColor, mBackgroundEndColor);
            mPaintBg.setShader(gradient);
            mBackgroundStartColor = 0;
            mBackgroundEndColor =0;
        }
        canvas.drawArc(mRect, 0, 360, false, mPaintBg);
        canvas.drawArc(mRect, 0, mAngle, false, mPaintFr);
    }

    public void setStartAngle(float start) {
        setRotation(start);
        postInvalidate();
    }

    public void setAngle(float angle) {
        mAngle = angle;
        postInvalidate();
    }
}
