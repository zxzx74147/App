package com.zxzx74147.qiushi.databinding;

import android.databinding.BindingAdapter;
import android.text.SpannableString;
import android.widget.TextView;

import com.zxzx74147.qiushi.common.data.CommentData;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiTextBindingUtil {

    @BindingAdapter({"txt_hot_comment"})
    public static void setHotComment(TextView textView, CommentData comment) {
        if(comment ==null){
            return;
        }
        String userName = null;
        if(comment.user!=null) {
            userName= comment.user.login;
        }
        if(userName!=null){

        }
        SpannableString mSpannable = new SpannableString(userName+":"+comment.content);
        textView.setText(mSpannable);
    }
}
