package com.zxzx74147.dksq.modules.account;

import com.zxzx74147.devlib.callback.ICommonCallback;
import com.zxzx74147.devlib.storage.ZXKVStore;
import com.zxzx74147.devlib.utils.BdLog;
import com.zxzx74147.devlib.utils.ZXJsonUtil;
import com.zxzx74147.devlib.utils.ZXSharedPreferenceHelper;
import com.zxzx74147.devlib.utils.ZXStringUtil;
import com.zxzx74147.dksq.modules.data.UserData;

import cn.myhug.common.key.AllKeys;

/**
 * Created by zhengxin on 2017/2/4.
 */

public class AccountManager {
    private static final String TAG = AccountManager.class.getName();
    private static final boolean VERBOSE = true;
    private UserData mUserData = null;
    private String mToken = null;
    private String mUid = null;

    private static AccountManager mInstance;

    private AccountManager() {
        mToken = ZXSharedPreferenceHelper.getString(AllKeys.KEY_TOKEN, null);
        mUid = ZXSharedPreferenceHelper.getString(AllKeys.KEY_UID, null);
        ZXKVStore.getAsync(AllKeys.KEY_USER_DATA, null, UserData.class, new ICommonCallback<UserData>() {
            @Override
            public int callback(UserData arg) {
                mUserData = arg;
                if(VERBOSE)BdLog.i(TAG, "user_data get", ZXJsonUtil.toJsonString(mUserData));
                return 0;
            }
        });
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

    public String getUid() {
        return mUid;
    }

    public boolean isLogin() {
        return ZXStringUtil.checkString(mToken);
    }

    public void logout() {
        mToken = null;
        mUid = null;
        ZXSharedPreferenceHelper.saveString(AllKeys.KEY_UID, null);
        ZXSharedPreferenceHelper.saveString(AllKeys.KEY_TOKEN, null);
        ZXKVStore.setAsync(AllKeys.KEY_USER_DATA,null);
    }

    public void saveUser(UserData mData) {
        mUserData = mData;
        mToken = mUserData.token;
        mUid = mUserData.id;
        ZXSharedPreferenceHelper.saveString(AllKeys.KEY_UID, mUid);
        ZXSharedPreferenceHelper.saveString(AllKeys.KEY_TOKEN, mToken);
        ZXKVStore.setAsync(AllKeys.KEY_USER_DATA, mUserData);
        if(VERBOSE)BdLog.i(TAG, "user_data set", ZXJsonUtil.toJsonString(mUserData));
    }
}
