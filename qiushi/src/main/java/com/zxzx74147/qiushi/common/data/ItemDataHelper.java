package com.zxzx74147.qiushi.common.data;

/**
 * Created by zhengxin on 16/9/7.
 */

public class ItemDataHelper {
    public static boolean isVideo(ItemData data){
        if(data!=null&&"video".equals(data.format)){
            return true;
        }
        return false;
    }

    public static boolean isImage(ItemData data) {
        if(data!=null&&"image".equals(data.format)){
            return true;
        }
        return false;
    }

    public static boolean hasImage(ItemData data) {
        if(data==null){
            return false;
        }
        if(data.image_size==null){
            return false;
        }
        return true;
    }

    public static boolean isWord(ItemData data) {
        if(data!=null&&"word".equals(data.format)){
            return true;
        }
        return false;
    }
}
