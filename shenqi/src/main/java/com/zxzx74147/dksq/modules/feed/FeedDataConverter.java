package com.zxzx74147.dksq.modules.feed;

import com.zxzx74147.devlib.data.BaseItemData;
import com.zxzx74147.dksq.R;
import com.zxzx74147.dksq.modules.data.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengxin on 16/8/22.
 */

public class FeedDataConverter {

    public static  List<BaseItemData> convertData(List<ItemData> datas) {
        if (datas == null) {
            return new ArrayList<>(0);
        }
        List<BaseItemData> result = new ArrayList<>(datas.size());

        for (ItemData data : datas) {
            BaseItemData<ItemData> itemData = new BaseItemData<>();
            if("image".equals(data.format)){
                itemData.setItemType(R.layout.item_image);
            }else if("video".equals(data.format)){
                itemData.setItemType(R.layout.item_video);
            }else {
                itemData.setItemType(R.layout.item_common);
            }
            itemData.data = data;
            result.add(itemData);
        }
        return result;
    }
}
