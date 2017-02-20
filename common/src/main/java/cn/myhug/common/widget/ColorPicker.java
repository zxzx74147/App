package cn.myhug.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

import com.jrummyapps.android.colorpicker.ColorPickerDialog;
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;

import cn.myhug.common.R;

/**
 * Created by zhengxin on 2017/2/17.
 */

public class ColorPicker extends View {

    private int mColor = 0;

    public ColorPicker(Context context) {
        super(context);
    }

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ColorPicker);
        mColor = a.getColor(R.styleable.ColorPicker_zx_color, 0);
        setColor(mColor);
        a.recycle();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerDialog();
            }
        });
    }

    public void setColor(int color) {
        mColor = color;
        setBackgroundColor(mColor);
    }

    public int getColor() {
        return mColor;
    }

    private void showPickerDialog() {
        int[] color = getResources().getIntArray(R.array.demo_colors);
        ColorPickerDialog dialog = ColorPickerDialog.newBuilder()
                .setColor(mColor)
                .setPresets(color)
                .create();
        dialog.setColorPickerDialogListener(mListener);
        dialog.show(((Activity)getContext()).getFragmentManager(), "color-picker-dialog");
    }

    private ColorPickerDialogListener mListener = new ColorPickerDialogListener() {
        @Override
        public void onColorSelected(int dialogId, @ColorInt int color) {
            setColor(mColor);
        }

        @Override
        public void onDialogDismissed(int dialogId) {

        }
    };
}
