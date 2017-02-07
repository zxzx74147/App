package cn.myhug.common.device;

import android.graphics.Point;
import android.util.DisplayMetrics;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 2017/2/4.
 */

public class DeviceInfoHelper {

    private static DisplayMetrics mDisplayMetrics = null;

    static {
        mDisplayMetrics = ZXApplicationDelegate.getApplication().getResources().getDisplayMetrics();
    }

    public static Point getScreenSizePixel(){
        Point size = new Point(mDisplayMetrics.widthPixels,mDisplayMetrics.heightPixels);
        return size;
    }
}
