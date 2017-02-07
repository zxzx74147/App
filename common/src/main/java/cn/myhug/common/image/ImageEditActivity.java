package cn.myhug.common.image;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.myhug.common.R;
import cn.myhug.common.databinding.ActivityImageEditBinding;

public class ImageEditActivity extends AppCompatActivity {

    ActivityImageEditBinding mBinding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_image_edit);
    }
}
