package cn.myhug.common.utils;

import android.app.Activity;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;

import cn.myhug.common.callback.IFileSelectCallback;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by zhengxin on 2017/2/9.
 */

public class ImageSelectHelper {

    private static LruCache<Integer,IFileSelectCallback> mTables = new LruCache<>(2);
    private static int mRequestOffset = 0;

    public static void selectImageSingle(Activity activity,IFileSelectCallback callback){
        mRequestOffset++;
        mRequestOffset%=RequestCode.REQUEST_FRAGMENT;
        int id = RequestCode.REQUEST_SELECT_IMAGE+mRequestOffset;
        mTables.put(id,callback);
        MultiImageSelector.create()
                .showCamera(true) // show camera or not. true by default
                .single() // single mode
                .start(activity, id);

    }

    public static void selectImageMulti(Activity activity, ArrayList<String> origin, IFileSelectCallback callback){
        mRequestOffset++;
        mRequestOffset%=RequestCode.REQUEST_FRAGMENT;
        int id = RequestCode.REQUEST_SELECT_IMAGE+mRequestOffset;
        mTables.put(id,callback);
        MultiImageSelector.create()
                .showCamera(true)
                .count(9)
                .origin(origin)
                .multi()
                .start(activity, id);
    }

    public static void dealResult(int requestCode,int resultCode, List<String> files){
        IFileSelectCallback callback = mTables.remove(requestCode);
        if(callback!=null&&resultCode==Activity.RESULT_OK){
            callback.onFileSelect(files);
        }
    }
}
