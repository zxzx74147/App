package cn.myhug.common.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 2017/1/23.
 */

class ZXGoogleLocationProvider {
    private LocationManager mLocationManager = null;

    public boolean init() {
        mLocationManager = (LocationManager)
                ZXApplicationDelegate.getApplication().getSystemService(Context.LOCATION_SERVICE);
        return true;
    }

    public void reqeustLocation() {
        if (ActivityCompat.checkSelfPermission(ZXApplicationDelegate.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(ZXApplicationDelegate.getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 50, mLocationListener);
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
