package com.zxzx74147.dksq.modules.launcher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.modules.action.ActionRecordActivity;

public class TestActivity extends ZXBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZXActivityJumpHelper.startActivity(TestActivity.this, ActionRecordActivity.class);
            }
        });
    }

}
