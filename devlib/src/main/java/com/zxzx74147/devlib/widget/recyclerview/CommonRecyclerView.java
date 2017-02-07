package com.zxzx74147.devlib.widget.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.zxzx74147.devlib.data.BaseItemData;

import java.util.List;

/**
 * Created by zhengxin on 16/8/21.
 */

public class CommonRecyclerView extends RecyclerView {
    public CommonRecyclerView(Context context) {
        super(context);
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void openPreload(){
        addOnScrollListener(mOnScrollListener);
    }

    public void closePreload(){
        removeOnScrollListener(mOnScrollListener);
    }

    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState==RecyclerView.SCROLL_STATE_IDLE){
                preload();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy){

        }
    };



    public void preload(){
        if(!(getLayoutManager() instanceof LinearLayoutManager)){
            return;
        }
        LinearLayoutManager lm = (LinearLayoutManager) getLayoutManager();
        CommonRecyclerViewAdapter adapter = (CommonRecyclerViewAdapter) getAdapter();
        int first =lm.findFirstVisibleItemPosition();
        int last = lm.findLastCompletelyVisibleItemPosition();
        int headerCount = adapter.getHeaderLayoutCount();
        first-=headerCount;
        last-=headerCount;
        int preCount =4;
        last = Math.max(0,last);
        List<BaseItemData> list = adapter.getData();

        for(int i=last;i<list.size();i++){
            list.get(i).preload();
        }


    }
}
