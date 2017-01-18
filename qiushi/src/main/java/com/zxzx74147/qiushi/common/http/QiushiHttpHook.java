package com.zxzx74147.qiushi.common.http;

import com.zxzx74147.devlib.http.ZXHttpHook;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.utils.ZXStringUtil;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiHttpHook implements ZXHttpHook {

    private static int mRqcnt = 1;
    private static String r = "f0a8601c";

    @Override
    public void onSendRequest(ZXHttpRequest request) {
        request.addParam("rqcnt", mRqcnt++);
        request.addParam("r", r + System.currentTimeMillis());
        String readList = QiushiReadList.getList();
        if(ZXStringUtil.checkString(readList)) {
            request.addParam("readarticles", readList);
        }


        request.addHeader("User-Agent", "qiushibalke_10.1.3_WIFI_auto_6");
        request.addHeader("Host", "m2.qiushibaike.com");
        request.addHeader("Model", "google/shamu/shamu:6.0.1/MOB31E/3142026:user/release-keys");
        request.addHeader("Uuid","IMEI_b129aed9b05ccd2b60bee46ff0a8601c");

        request.addHeader("Deviceidinfo","{\"DEVICEID\":\"355470062561036\",\"SIMNO\":\"89860112811013537523\",\"IMSI\":\"460012315300409\",\"ANDROID_ID\":\"5b499cb0048135cb\",\"SDK_INT\":23,\"SERIAL\":\"ZX1G42BCLL\",\"MAC\":\"02:00:00:00:00:00\",\"RANDOM\":\"\"}");
    }
}
