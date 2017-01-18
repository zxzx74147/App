package com.zxzx74147.dksq.modules.feed;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.zxzx74147.devlib.base.ZXBaseFragment;
import com.zxzx74147.devlib.data.BaseItemData;
import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.widget.recyclerview.CommonRecyclerViewAdapter;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.FragmentFeedListBinding;
import com.zxzx74147.dksq.modules.data.ItemListData;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends ZXBaseFragment {

    private FragmentFeedListBinding mBinding = null;
    private ZXHttpRequest<ItemListData> mRequestRefresh = null;
    private ZXHttpRequest<ItemListData> mRequestMore = null;
    private CommonRecyclerViewAdapter mAdapter = null;
    private List<BaseItemData> mData = new ArrayList<>();

    public static FeedFragment newInstance(int columnCount) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed_list, container, false);
        mBinding.setHandler(this);
        mBinding.list.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
        mAdapter = new CommonRecyclerViewAdapter(new FeedRecyclerViewTable(), mData);
        mBinding.list.setAdapter(mAdapter);
        mBinding.refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                loaeMore();
            }
        });
        mBinding.refresh.setLoadMore(true);
        refresh();
        return mBinding.getRoot();
    }

    private void refresh() {
        if (mRequestRefresh != null) {
            mRequestRefresh.cancel();
            mRequestRefresh = null;
        }
        mRequestRefresh = getRequest(ItemListData.class);
        mRequestRefresh.setUrl("http://blog.armcv.com/list/50");
        mRequestRefresh.send(new ZXHttpCallback<ItemListData>() {
            @Override
            public void onResponse(ZXHttpResponse<ItemListData> response) {
                mBinding.refresh.finishRefresh();
                mBinding.refresh.finishRefreshLoadMore();
                if (response.isSuccess()) {
                    List<BaseItemData> add = FeedDataConverter.convertData(response.mData.list);
                    mData.clear();
                    mData.addAll(add);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loaeMore() {
        if (mRequestMore != null) {
            mRequestMore.cancel();
            mRequestMore = null;
        }
        mRequestMore = getRequest(ItemListData.class);
        mRequestMore.setUrl("http://blog.armcv.com/list/50");
        mRequestMore.send(new ZXHttpCallback<ItemListData>() {
            @Override
            public void onResponse(ZXHttpResponse<ItemListData> response) {
                mBinding.refresh.finishRefresh();
                mBinding.refresh.finishRefreshLoadMore();
                if (response.isSuccess()) {
                    List<BaseItemData> add = FeedDataConverter.convertData(response.mData.list);

                    mData.addAll(add);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onReselect() {
        mBinding.list.smoothScrollToPosition(0);
        mBinding.refresh.autoRefresh();
    }
}
