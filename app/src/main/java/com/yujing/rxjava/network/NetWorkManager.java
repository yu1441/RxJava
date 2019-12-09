package com.yujing.rxjava.network;


import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yujing.rxjava.BuildConfig;
import com.yujing.rxjava.constants.Constants;
import com.yujing.rxjava.network.request.YRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * API初始化类
 */
public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile YRequest request = null;
    private OkHttpClient mOkHttpClient;//自定义的OkHttpClient
    private static Gson gson;

    public static NetWorkManager getInstance() {
        if (mInstance == null)
            synchronized (NetWorkManager.class) {
                if (mInstance == null)
                    mInstance = new NetWorkManager();
            }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init(Context context) {
        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(okhttpclient(context))
                .baseUrl(Constants.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //支持Rxjava,这个是用来决定你的返回值是Observable还是Call
                .addConverterFactory(GsonConverterFactory.create())//将服务器返回的json字符串转化为对象。
                .build();
    }

    //请求的服务器的API封装类
    public static YRequest getRequest() {
        if (request == null)
            synchronized (YRequest.class) {
                request = retrofit.create(YRequest.class);
            }
        return request;
    }

    //初始化 OkHttpClient
    private OkHttpClient okhttpclient(Context context) {
        if (mOkHttpClient == null) {
            //显示日志
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                logInterceptor.level(HttpLoggingInterceptor.Level.NONE);
            }
            //创建OkHttpClient
            mOkHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).addInterceptor(logInterceptor)
                    .build();
        }
        return mOkHttpClient;
    }
}
