package com.zxzx74147.qiushi.common.http;

import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.qiushi.R;
import com.zxzx74147.qiushi.common.data.ImageSizeData;
import com.zxzx74147.qiushi.common.data.ItemData;
import com.zxzx74147.qiushi.common.data.ItemDataHelper;
import com.zxzx74147.qiushi.common.data.UserData;

import java.io.File;

/**
 * Created by zhengxin on 16/9/5.
 */

public class UrlFormat {

    private static final String PATH_PORTRAIT = ZXApplicationDelegate.getApplication().getResources().getString(R.string.http_host_img)+ZXApplicationDelegate.getApplication().getResources().getString(R.string.http_path_portrait);
    public static String fromatPortrait(UserData user){
        if(user == null){
            return null;
        }
        String path1= String.valueOf(user.id/10000);
        String path2 = String.valueOf(user.id);
        String fullpath = path1+"/"+path2+"/thumb/"+user.icon;
        fullpath = PATH_PORTRAIT+fullpath;
        return fullpath;
    }

    private static final String PATH_PICTURE = ZXApplicationDelegate.getApplication().getResources().getString(R.string.http_host_img)+ZXApplicationDelegate.getApplication().getResources().getString(R.string.http_path_img);
    public static String fromatImage(ItemData item){
        if(item == null||item.image==null){
            return null;
        }
        if(!ItemDataHelper.isImage(item)){
            return null;
        }
        String img = item.image;
        if(img.length()<7){
            return null;
        }
        String temp[] = img.split("\\.");
        if(temp.length!=2){
            return null;
        }
        img = temp[0];
        img = img.replaceFirst("app","");

        String path1= img.substring(0,img.length()-4);
        String fullpath = path1+"/"+img+"/medium/"+item.image;
        fullpath = PATH_PICTURE+fullpath;
        return fullpath;
    }
}
