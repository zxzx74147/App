package com.zxzx74147.devlib.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.TextView;

import com.zxzx74147.devlib.utils.ZXFontUtil;

//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.controller.BaseControllerListener;
//import com.facebook.drawee.controller.ControllerListener;
//import com.facebook.drawee.interfaces.DraweeController;

/**
 * Created by zhengxin on 15/9/25.
 */
public class TextBindUtil {

    public static final int NO_ID = -1;

    @BindingAdapter({"zx_font"})
    public static void loadImage(TextView textView, String path) {
        Typeface tf = ZXFontUtil.getTypeface(path);
        textView.setTypeface(tf);
    }

}
