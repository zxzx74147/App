package com.zxzx74147.qiushi.modules.list.recyclerview;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxzx74147.devlib.data.BaseItemData;

import java.util.List;

/**
 * Created by zhengxin on 16/8/21.
 */

public class CommonRecyclerViewAdapter extends BaseMultiItemQuickAdapter<BaseItemData> {
    private CommonRecyclerViewTable mTable = null;

    public CommonRecyclerViewAdapter(CommonRecyclerViewTable table, List<BaseItemData> data) {
        super(data);
        mTable = table;
        for (int i : mTable.getLayoutId()) {
            addItemType(i, i);
        }
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaseItemData baseItemData) {
        mTable.convert(baseViewHolder, baseItemData);
    }

    public int getDataCount(){
        return mData.size();
    }

}
