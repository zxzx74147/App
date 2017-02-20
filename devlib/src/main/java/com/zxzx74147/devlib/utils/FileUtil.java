package com.zxzx74147.devlib.utils;

import android.os.Environment;

import com.zxzx74147.devlib.ZXApplicationDelegate;

import java.io.File;

/**
 * Created by zhengxin on 2016/11/2.
 */

public class FileUtil {

    private static final String PATH_SD_CARD = ZXApplicationDelegate.getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();

    public static File getImageFile(String filename) {
        String dst = PATH_SD_CARD + File.separator + filename;
        File file = new File(dst);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public static void deleteImageFile(String filename) {
        String dst = PATH_SD_CARD + File.separator + filename;
        File file = new File(dst);
        deleteFile(file);
    }

    public static File getImageCacheFile(String filename) {
        String dst = PATH_SD_CARD + File.separator + "cache" + File.separator + filename;
        File file = new File(dst);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public static void deleteImageCacheFile(String filename) {
        String dst = PATH_SD_CARD + File.separator + "cache" + File.separator + filename;
        File file = new File(dst);
        deleteFile(file);
    }

    public static boolean deleteFile(File file){
        if(file==null||!file.exists()){
            return true;
        }
        return file.delete();
    }
}
