package com.zxzx74147.dksq.modules.account;

import com.zxzx74147.devlib.utils.ZXSharedPreferenceHelper;
import com.zxzx74147.devlib.utils.ZXStringUtil;
import com.zxzx74147.dksq.modules.data.UserData;

import cn.myhug.common.key.SPKeys;

/**
 * Created by zhengxin on 2017/2/4.
 */

public class AccountManager {

    private UserData mUserData = null;
    private String mToken = null;

    private static AccountManager mInstance;

    private AccountManager() {
        mToken = ZXSharedPreferenceHelper.getString(SPKeys.KEY_TOKEN, null);
    }

    public static AccountManager sharedInstance() {
        if (mInstance == null) {
            mInstance = new AccountManager();
        }
        return mInstance;
    }

    public UserData getUserData() {
        return mUserData;
    }

    public String getToken() {
        return mToken;
    }

    public boolean isLogin() {
        return ZXStringUtil.checkString(mToken);
    }

    public void logout() {
        mToken = null;
        ZXSharedPreferenceHelper.saveString(SPKeys.KEY_TOKEN, null);
    }

}
