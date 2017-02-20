package com.zxzx74147.dksq.modules.maintab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zxzx74147.devlib.utils.ZXActivityJumpHelper;
import com.zxzx74147.devlib.utils.ZXMonitorUtil;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityMainTabBinding;
import com.zxzx74147.dksq.modules.account.LoginActivity;
import com.zxzx74147.dksq.modules.action.ActionRecordActivity;

import cn.myhug.common.base.BaseActivity;

public class MainTabActivity extends BaseActivity{
    private ActivityMainTabBinding mBinding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_tab);

        setSupportActionBar(mBinding.content.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZXActivityJumpHelper.startActivity(MainTabActivity.this, ActionRecordActivity.class);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.content.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mBinding.setNavigationPresent(new NavigationPresent());

        postDelayed(new Runnable() {
            @Override
            public void run() {
                ZXMonitorUtil.getTopPackage();
            }
        },2000);

    }


    public class NavigationPresent implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                // Handle the camera action
                ZXActivityJumpHelper.startActivity(MainTabActivity.this, LoginActivity.class);
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
