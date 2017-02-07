package com.zxzx74147.dksq.maintab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;

import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.devlib.base.ZXBaseFragment;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ActivityMainBinding;
import com.zxzx74147.dksq.modules.feed.FeedFragment;

public class MainActivity extends ZXBaseActivity {

    private ActivityMainBinding mBinding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mBinding.ads.setVisibility(View.GONE);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mBinding.ads.loadAd(adRequest);
        mBinding.navigation.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.action_text) {
                    mBinding.viewpager.selectFragmentIndex(0,false);
                }
                if (tabId == R.id.action_image) {
                    mBinding.viewpager.selectFragmentIndex(1,false);
                }
                if (tabId == R.id.action_video) {
                    mBinding.viewpager.selectFragmentIndex(2,false);
                }
            }
        });

        mBinding.navigation.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                ZXBaseFragment fragment =mBinding.viewpager.getCurrentFragment();
                fragment.onReselect();
            }
        });
        initTabs();
    }

    private void initTabs(){
        mBinding.viewpager.setupFragmentManager(getSupportFragmentManager());
        FeedFragment fragment0 = new FeedFragment();
        int id= fragment0.getId();
        mBinding.viewpager.addFragment(fragment0);

        FeedFragment fragment1 = new FeedFragment();
        mBinding.viewpager.addFragment(fragment1);

        FeedFragment fragment3 = new FeedFragment();
        mBinding.viewpager.addFragment(fragment3);

        mBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.navigation.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
