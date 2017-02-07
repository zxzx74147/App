package com.zxzx74147.dksq.modules.feed;

import android.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zxzx74147.devlib.data.BaseItemData;
import com.zxzx74147.devlib.widget.recyclerview.CommonRecyclerViewTable;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.databinding.ItemCommonBinding;
import com.zxzx74147.dksq.databinding.ItemImageBinding;
import com.zxzx74147.dksq.databinding.ItemVideoBinding;
import com.zxzx74147.dksq.modules.data.ItemData;


public class FeedRecyclerViewTable implements CommonRecyclerViewTable {
    private int[] mTable = new int[]{
            R.layout.item_common,R.layout.item_image,R.layout.item_video
    };

    @Override
    public int[] getLayoutId() {
        return mTable;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, BaseItemData baseItemData) {

        switch (baseItemData.getItemType()) {

            case R.layout.item_common: {
                ItemCommonBinding itemCommonBinding = DataBindingUtil.bind(baseViewHolder.convertView);
                itemCommonBinding.setItem((ItemData) baseItemData.data);
                itemCommonBinding.getRoot().setTag(R.id.tag_holder, itemCommonBinding);
                break;
            }
            case R.layout.item_image: {
                ItemImageBinding itemCommonBinding = DataBindingUtil.bind(baseViewHolder.convertView);
                itemCommonBinding.setItem((ItemData) baseItemData.data);
                itemCommonBinding.getRoot().setTag(R.id.tag_holder, itemCommonBinding);
                break;
            }
            case R.layout.item_video: {
                ItemVideoBinding itemCommonBinding = DataBindingUtil.bind(baseViewHolder.convertView);
                itemCommonBinding.setItem((ItemData) baseItemData.data);
                itemCommonBinding.getRoot().setTag(R.id.tag_holder, itemCommonBinding);
                break;
            }
        }
    }
}
