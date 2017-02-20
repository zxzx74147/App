package com.zxzx74147.devlib.utils;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.zxzx74147.devlib.ZXApplicationDelegate;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Created by zhengxin on 2017/2/20.
 */

public class ZXMonitorUtil {

    public static void requestPermission() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ZXApplicationDelegate.getApplication().startActivity(intent);
    }

    public static String getTopPackage() {
        String currentApp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usm = (UsageStatsManager) ZXApplicationDelegate.getApplication().getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST,
                    time - TimerUtil.HOUR, time);
            if (appList != null && appList.size() > 0) {

                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<>();
                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(),
                            usageStats);
                }
                if ( !mySortedMap.isEmpty()) {

                    currentApp = mySortedMap.get(
                            mySortedMap.lastKey()).getPackageName();
                }
                for (UsageStats usageStats : mySortedMap.values()) {
                    BdLog.i(usageStats.getPackageName()+usageStats.getLastTimeUsed());
                }

            }
        } else {
            ActivityManager am = (ActivityManager) ZXApplicationDelegate.getApplication().getSystemService(Context.ACTIVITY_SERVICE);
            currentApp = am.getRunningTasks(1).get(0).topActivity.getPackageName();
        }

        return currentApp;
    }
}
