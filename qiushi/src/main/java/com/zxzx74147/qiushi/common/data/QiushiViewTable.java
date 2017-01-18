package com.zxzx74147.qiushi.common.data;

import android.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zxzx74147.devlib.data.BaseItemData;
import com.zxzx74147.devlib.widget.recyclerview.CommonRecyclerViewTable;
import com.zxzx74147.qiushi.R;
import com.zxzx74147.qiushi.databinding.ItemQiushiBinding;

/**
 * Created by zhengxin on 16/8/21.
 */

public class QiushiViewTable implements CommonRecyclerViewTable {
    private int[] mTable = new int[]{
            R.layout.item_qiushi
    };

    @Override
    public int[] getLayoutId() {
        return mTable;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, BaseItemData baseItemData) {

        switch (baseItemData.getItemType()) {
            case R.layout.item_qiushi:
                ItemQiushiBinding itemQiushiBinding = DataBindingUtil.bind(baseViewHolder.convertView);
                itemQiushiBinding.setItem((ItemData) baseItemData.data);
                itemQiushiBinding.getRoot().setTag(R.id.tag_holder,itemQiushiBinding);
                break;
        }
    }
}
