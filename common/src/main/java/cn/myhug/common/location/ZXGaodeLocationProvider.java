package cn.myhug.common.location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zxzx74147.devlib.ZXApplicationDelegate;

/**
 * Created by zhengxin on 2017/1/23.
 */

 class ZXGaodeLocationProvider {
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mLocationClient = null;

    public void init() {
        mLocationClient = new AMapLocationClient(ZXApplicationDelegate.getApplication());
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationCacheEnable(true);
//设置定位监听
        mLocationClient.setLocationListener(mListener);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public void requestLocation() {
        mLocationClient.startLocation();
    }

    private AMapLocationListener mListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            mLocationClient.stopLocation();
        }
    };

// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
}
