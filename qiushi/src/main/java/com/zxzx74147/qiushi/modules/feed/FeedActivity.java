package com.zxzx74147.qiushi.modules.feed;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.zxzx74147.devlib.base.ZXBaseActivity;
import com.zxzx74147.devlib.data.BaseItemData;
import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.widget.recyclerview.CommonRecyclerViewAdapter;
import com.zxzx74147.qiushi.R;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.QiushiViewTable;
import com.zxzx74147.qiushi.common.data.SuggestData;
import com.zxzx74147.qiushi.common.data.TagConfig;
import com.zxzx74147.qiushi.common.http.QiushiReadList;
import com.zxzx74147.qiushi.databinding.ActivityFeedBinding;
import com.zxzx74147.qiushi.modules.list.recyclerview.CommonDataConverter;

import java.util.LinkedList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class FeedActivity extends ZXBaseActivity {

    private ActivityFeedBinding mBinding = null;
    private CommonRecyclerViewAdapter mAdapter = null;
    private List<BaseItemData> mList = new LinkedList<>();
    protected LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    protected ZXHttpRequest mRefreshRrequest = null;
    protected ZXHttpRequest mLoadMoreRrequest = null;
    private TagConfig mTagConfig = null;
    private int mPage = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        mAdapter = new CommonRecyclerViewAdapter(new QiushiViewTable(), mList);
        mBinding.listview.setAdapter(mAdapter);
        mBinding.listview.setLayoutManager(mLayoutManager);
        mBinding.refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refresh();
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mTagConfig = (TagConfig) getParams();
        mBinding.listview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE ){
                   onIdle();
                }
            }
        });
    }

    private void onIdle(){
        int start = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        int end = mLayoutManager.findLastCompletelyVisibleItemPosition();
        int header = mAdapter.getHeaderLayoutCount();
//        View itemView = mLayoutManager.findViewByPosition(start);
//        Object holder = itemView.getTag(R.id.tag_holder);
//        if(holder instanceof ItemQiushiBinding){
//            ((ItemQiushiBinding) holder).getItem()
//        }
        start-=header;
        end-=header;
        start = Math.max(0,start);
        end = Math.min(mAdapter.getDataCount()-1,end);
        for(int i=start;i<=end;i++){
            Object item = mAdapter.getItem(i);
            if(item instanceof BaseItemData&&((BaseItemData) item).data instanceof ItemData){
                QiushiReadList.addList(((ItemData)((BaseItemData) item).data).id);
            }
        }

    }

    @Override
    protected void refresh() {
        if (mTagConfig == null) {
            return;
        }
        if (mRefreshRrequest != null) {
            mRefreshRrequest.cancel();
        }
        mRefreshRrequest = getRequest(SuggestData.class);
        mRefreshRrequest.setUrl(mTagConfig.url);
        mRefreshRrequest.addParam("page", 1);
        mRefreshRrequest.addParam("count", 30);
        mRefreshRrequest.send(new ZXHttpCallback<SuggestData>() {
            @Override
            public void onResponse(ZXHttpResponse<SuggestData> response) {
                mBinding.refresh.finishRefresh();
                if (!response.isSuccess()) {
                    mRefreshRrequest = null;
                    return;
                }
                if (mLoadMoreRrequest != null) {
                    mLoadMoreRrequest.cancel();
                    mLoadMoreRrequest = null;
                }
                if (response.isSuccess()) {
                    SuggestData data = response.mData;
                    mBinding.setList(data);
                    mAdapter.getData().clear();
                    mAdapter.addData(CommonDataConverter.convertData(R.layout.item_qiushi, data.items));
                    mPage = data.page;
                }
                mRefreshRrequest = null;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onIdle();
                    }
                },10);
            }
        });
    }

    protected void loadMore() {
        if (mTagConfig == null) {
            return;
        }
        if (mPage < 0) {
            return;
        }
        if (mLoadMoreRrequest != null) {
            return;
        }
        mLoadMoreRrequest = getRequest(SuggestData.class);
        mLoadMoreRrequest.setUrl(mTagConfig.url);
        mLoadMoreRrequest.addParam("page", mPage + 1);
        mLoadMoreRrequest.addParam("count", 30);
        mLoadMoreRrequest.send(new ZXHttpCallback<SuggestData>() {
            @Override
            public void onResponse(ZXHttpResponse<SuggestData> response) {
                mAdapter.loadComplete();
                if (!response.isSuccess()) {
                    mAdapter.showLoadMoreFailedView();
                    mLoadMoreRrequest = null;
                    return;
                }
                if (mLoadMoreRrequest != null) {
                    mLoadMoreRrequest.cancel();
                    mLoadMoreRrequest = null;
                }
                if (response.isSuccess()) {
                    SuggestData data = response.mData;
                    mBinding.setList(data);
                    mAdapter.addData(CommonDataConverter.convertData(R.layout.item_qiushi, data.items));
                    mPage = data.page;
                }
                mLoadMoreRrequest = null;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onIdle();
                    }
                },10);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
