package cn.myhug.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import com.zxzx74147.devlib.base.ZXBaseFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengxin on 2017/1/12.
 */

public class FragmentViewPager extends ViewPager {

    private MyFragmentPagerAdapter mAdapter = null;
    private SparseIntArray mTable = new SparseIntArray();

    public FragmentViewPager(Context context) {
        super(context);
    }

    public FragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupFragmentManager(FragmentManager fm){
        mAdapter = new MyFragmentPagerAdapter(fm);
        setAdapter(mAdapter);
    }

    public void addFragment(ZXBaseFragment fragment){
        int index = mAdapter.addFragment(fragment);
        mTable.put(fragment.getId(),index);
        mAdapter.notifyDataSetChanged();
    }

    public void selectFragmentId(int id,boolean smooth){
        int index = mTable.get(id);
        if(index<0){
            return;
        }
        setCurrentItem(index,smooth);
    }
    public void selectFragmentIndex(int index,boolean smooth){
        setCurrentItem(index,smooth);
    }

    public ZXBaseFragment getCurrentFragment(){
        return mAdapter.getFragment(getCurrentItem());
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private List<ZXBaseFragment> mFragments = new LinkedList<>();

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public int addFragment(ZXBaseFragment fragment){
            int index = mFragments.size();
            mFragments.add(fragment);
            return index;
        }

        public ZXBaseFragment getFragment(int index){
            return mFragments.get(index);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
