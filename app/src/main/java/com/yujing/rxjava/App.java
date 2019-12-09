package com.yujing.rxjava;

import android.app.Application;
import android.util.Log;

import com.yujing.rxjava.network.NetWorkManager;
import com.yujing.ycrash.YCrash;

public class App extends Application {
    public static App INSTANCE;

    public static App getInstance() {
        if (INSTANCE == null) {
            synchronized (App.class) {
                if (INSTANCE == null) {
                    INSTANCE = new App();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        //初始化网络参数
        NetWorkManager.getInstance().init(getBaseContext());
        // 异常崩溃拦截写入日志到本地
        YCrash.getInstance().init(this);
        YCrash.getInstance().setAppName(getResources().getString(R.string.app_name));
        YCrash.getInstance().setCrashInfoListener(appInfo -> Log.e("崩溃拦截", appInfo.崩溃信息));
    }
}
