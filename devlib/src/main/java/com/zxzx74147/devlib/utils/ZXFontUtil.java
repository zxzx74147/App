package com.zxzx74147.devlib.utils;

import android.graphics.Typeface;
import android.util.LruCache;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 15/9/15.
 */
public class ZXFontUtil {
    private static LruCache<String, Typeface> mFontCache = new LruCache<>(5);

    public static Typeface getTypeface(String path) {
        Typeface type = mFontCache.get(path);
        if (type == null) {
            type = Typeface.createFromAsset(ZXApplicationDelegate.getApplication().getAssets(),
                    path);
            mFontCache.put(path, type);
        }
        return type;

    }
}
