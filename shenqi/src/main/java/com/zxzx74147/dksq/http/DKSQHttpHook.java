package com.zxzx74147.dksq.http;

import com.zxzx74147.devlib.http.ZXHttpHook;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.dksq.modules.account.AccountManager;

import cn.myhug.common.device.DeviceIdHelper;

/**
 * Created by zhengxin on 2017/2/16.
 */

public class DKSQHttpHook implements ZXHttpHook {
    @Override
    public void onSendRequest(ZXHttpRequest request) {
        request.addParam("token", AccountManager.sharedInstance().getToken());
        request.addParam("uid", AccountManager.sharedInstance().getUid());
        request.addParam("device_id", DeviceIdHelper.getDeviceId());
    }
}
