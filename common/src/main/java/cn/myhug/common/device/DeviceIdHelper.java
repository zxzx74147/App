package cn.myhug.common.device;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.zxzx74147.devlib.ZXApplicationDelegate;
import com.zxzx74147.devlib.utils.BdLog;
import com.zxzx74147.devlib.utils.ZXSharedPreferenceHelper;
import com.zxzx74147.devlib.utils.ZXStringUtil;

import cn.myhug.common.key.AllKeys;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by zhengxin on 2017/2/4.
 */

public class DeviceIdHelper {
    private static final String TAG = DeviceIdHelper.class.getName();
    private static String ID = null;

    static {
        ID = ZXSharedPreferenceHelper.getString(AllKeys.KEY_DEVIDE_ID, null);
        if (!ZXStringUtil.checkString(ID)) {
            TelephonyManager tm = ((TelephonyManager) ZXApplicationDelegate.getApplication().getSystemService(TELEPHONY_SERVICE));
            String imei = tm.getDeviceId();

            WifiManager wifiMng = (WifiManager) ZXApplicationDelegate.getApplication().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfor = wifiMng.getConnectionInfo();
            String mac = wifiInfor.getMacAddress();

            String ANDROID_ID = Settings.System.getString(ZXApplicationDelegate.getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);

            StringBuilder sb = new StringBuilder(64);
            sb.append(imei);
            sb.append(mac);
            sb.append(ANDROID_ID);
            ID = sb.toString();
            ZXSharedPreferenceHelper.saveString(AllKeys.KEY_DEVIDE_ID, ID);
            BdLog.i(TAG, "getDeviceId", "device id=" + ID);
        }
    }

    public static String getDeviceId() {
        return ID;
    }
}
