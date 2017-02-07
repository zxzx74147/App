package cn.myhug.common.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 2017/1/23.
 */

public class ZXLocationManager {

    private static ZXLocationManager mInstance = null;
    private ZXGoogleLocationProvider mGoogleProvider = null;
    private ZXGaodeLocationProvider mGaodeProvider = null;

    private ZXLocationManager(){
        mGoogleProvider = new ZXGoogleLocationProvider();
        mGaodeProvider = new ZXGaodeLocationProvider();
    }

    public static final ZXLocationManager sharedInstance(){
        if(mInstance == null){
            mInstance = new ZXLocationManager();
        }
        return mInstance;
    }

    public void checkPermission(){
//        int result = PermissionHelper.checkPermission(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION
//                , Manifest.permission.ACCESS_COARSE_LOCATION});
        if (ActivityCompat.checkSelfPermission(ZXApplicationDelegate.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(ZXApplicationDelegate.getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
    }

    public void requestLocation(){

    }
}
