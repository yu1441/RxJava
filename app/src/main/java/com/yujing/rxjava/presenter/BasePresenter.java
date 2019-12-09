package com.yujing.rxjava.presenter;

import com.yujing.rxjava.contract.OnResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 网络实现请求基类
 *
 * @author yujing 2019年11月8日10:46:39
 */
public abstract class BasePresenter {
    /**
     * 网络回复类
     */
    public OnResponse onResponse;
    /**
     * RxJava解耦
     */
    private CompositeDisposable mDisposable;

    public BasePresenter(OnResponse onResponse) {
        this.onResponse = onResponse;
    }

    public void addDisposable(Disposable disposable) {
        if (mDisposable == null)
            mDisposable = new CompositeDisposable();
        mDisposable.add(disposable);
    }

    public void onDestroy() {
        if (mDisposable != null)
            this.mDisposable.clear();
    }
}

