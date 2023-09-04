package com.yujing.rxjava;

import android.app.Application;
import android.util.Log;

import com.yujing.rxjava.network.NetWorkManager;

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
    }
}
