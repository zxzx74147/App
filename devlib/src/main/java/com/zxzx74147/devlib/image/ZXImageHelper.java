package com.zxzx74147.devlib.image;

import android.graphics.Bitmap;

import com.zxzx74147.devlib.utils.ZXFileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhengxin on 2017/2/13.
 */

public class ZXImageHelper {
    public static File saveImage(Bitmap bm, String path) {
        File file = ZXFileUtil.getImageCacheFile(path);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
            fOut.flush();
            fOut.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
