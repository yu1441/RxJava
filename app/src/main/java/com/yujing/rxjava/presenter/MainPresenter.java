package com.yujing.rxjava.presenter;

import android.util.Log;

import com.blankj.rxbus.RxBus;
import com.yujing.rxjava.contract.OnResponse;
import com.yujing.rxjava.network.Exception.YResponseTransformer;
import com.yujing.rxjava.network.NetWorkManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 主页网络请求功能实现
 *
 * @author yujing 2019年11月8日10:51:06
 */
public class MainPresenter extends BasePresenter {
    final String TAG = "MainPresenter";

    public MainPresenter(OnResponse onResponse) {
        super(onResponse);
    }

    public void get(String city) {
        Disposable disposable = NetWorkManager.getRequest().get(city)
                .compose(YResponseTransformer.handleResult())
                .subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(obj -> {
                    onResponse.onSuccess("", obj);
                }, throwable -> {
                    Log.e(TAG, "错误原因：" + throwable.getMessage(), throwable);
                    onResponse.onFail("错误原因：" + throwable.getMessage());
                });
        addDisposable(disposable);
    }

    public void getCity(String city) {
        Disposable disposable = NetWorkManager.getRequest().get(city)
                .compose(YResponseTransformer.handleResult())
                .subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(obj -> {
                    RxBus.getDefault().post(obj);
                }, throwable -> {
                    Log.e(TAG, "错误原因：" + throwable.getMessage(), throwable);
                });
        addDisposable(disposable);
    }

    public void get2() {
        Disposable disposable = NetWorkManager.getRequest().get2()
                .compose(YResponseTransformer.handleResult())
                .subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(obj -> {
                    onResponse.onSuccess("", obj);
                }, throwable -> {
                    Log.e(TAG, "错误原因：" + throwable.getMessage(), throwable);
                    onResponse.onFail("错误原因：" + throwable.getMessage());
                });
        addDisposable(disposable);
    }
}
