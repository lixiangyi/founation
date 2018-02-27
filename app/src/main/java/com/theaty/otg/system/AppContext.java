package com.theaty.otg.system;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.utils.Utils;
import com.theaty.otg.db.DBOpenHelper;
import com.theaty.otg.db.HistoryDataManager;
import com.umeng.socialize.PlatformConfig;

import foundation.toast.ToastManager;
import foundation.util.DataCleanManager;
import foundation.util.MethodsCompat;
import foundation.util.ThreadUtils;

public class AppContext extends CrashReportingApplication {

    private static final String TAG = AppContext.class.getSimpleName();
    private static AppContext mContext;
    private DBOpenHelper dbOpenHelper;
    private HistoryDataManager historyDataManager;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 工具类初始化
        Utils.init(this);

        // 个推初始化
//        PushManager.getInstance().initialize(this);

        // 效果图比对功能
//		if (BuildConfig.DEBUG) {
//			startService(new Intent(this, ControlWindowService.class));
//		}

        // 友盟初始化，未申请到各平台key时请注释友盟初始化方法
//        Config.DEBUG = true;
//        Config.REDIRECT_URL = "http://sns.whalecloud.com/sin2/callback";
//        UMShareAPI.get(this);
//        Config.isJumptoAppStore = true;
    }

    public DBOpenHelper getDBOpenHelper() {
        if (dbOpenHelper == null) {
            dbOpenHelper = new DBOpenHelper(this, DatasStore.getUserPhone());
        }
        return dbOpenHelper;
    }

    public HistoryDataManager getHistoryDataManager() {
        if (historyDataManager == null) {
            historyDataManager = new HistoryDataManager(this, getDBOpenHelper().getReadableDatabase());
        }
        return historyDataManager;
    }

    @Override
    public String getReportUrl() {
        return null;
    }

    @Override
    public Bundle getCrashResources() {
        return null;
    }

    //dex65k限制
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AppContext getInstance() {
        return mContext;
    }

    {
        PlatformConfig.setWeixin("wx55b0d50e6c6d05de", "a3103371ecaa7bfe1c87576e5f3599c9");
        PlatformConfig.setSinaWeibo("2769240067", "a06ddd4a7022ef3faa4232ee302f5d65");
        PlatformConfig.setQQZone("1106010398", "35ii0o8IvMMslX7T");
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache(boolean showToast) {
        final Handler handler = showToast ? new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    ToastManager.manager.show("缓存清除成功");
                } else {
                    ToastManager.manager.show("缓存清除失败");
                }
            }
        } : null;
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    clearAppCache();
                    msg.what = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = -1;
                }
                if (handler != null)
                    handler.sendMessage(msg);
            }
        });
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        DataCleanManager.cleanDatabases(this);
        // 清除数据缓存
        DataCleanManager.cleanInternalCache(this);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            DataCleanManager.cleanCustomCache(MethodsCompat
                    .getExternalCacheDir(this));
        }

    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }
}
