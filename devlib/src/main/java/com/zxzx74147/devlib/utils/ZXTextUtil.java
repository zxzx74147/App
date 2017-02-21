package com.zxzx74147.devlib.utils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by zhengxin on 15/9/15.
 */
public class ZXTextUtil {

    public static float mesureMaxSize( TextView text,int maxLen) {
        Paint paint = text.getPaint();
        String content = text.getText().toString();
        float size = paint.getTextSize();
        float length = paint.measureText(content);
        if(length==0||maxLen==0){
            return size;
        }
        float scale = maxLen/length;
        size*=scale;
        paint.setTextSize(size);
        text.setText(content);
        return size;
    }
}
