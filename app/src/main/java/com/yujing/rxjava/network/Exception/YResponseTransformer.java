package com.yujing.rxjava.network.Exception;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.yujing.rxjava.constants.Constants;
import com.yujing.rxjava.network.response.YResponse;
import com.yujing.rxjava.network.response.YResponseArray;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 对返回的数据进行处理，区分异常的情况。
 * </p>
 */

public class YResponseTransformer {
    /**
     * 返回整个T对象，如YResponse<T>,原样
     */
    public static <T> ObservableTransformer<T, T> handle() {
        return upstream -> upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                return Observable.error(exceptionHandle(throwable));
            }
        });
    }

    /**
     * 返回对象用到，用此方法最终会返回YResponse<T>中的T,剥壳壳
     */
    public static <T> ObservableTransformer<YResponse<T>, T> handleResult() {
        return upstream -> upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends YResponse<T>>>() {
            @Override
            public ObservableSource<? extends YResponse<T>> apply(Throwable throwable) throws Exception {
                return Observable.error(exceptionHandle(throwable));
            }
        }).flatMap(new YResponseFunction<>());
    }

    /**
     * 返回对象用到，用此方法最终会返回YResponse<T>,原样
     */
    public static <T> ObservableTransformer<YResponse<T>, YResponse<T>> handleResultOriginal() {
        return upstream -> upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends YResponse<T>>>() {
            @Override
            public ObservableSource<? extends YResponse<T>> apply(Throwable throwable) throws Exception {
                return Observable.error(exceptionHandle(throwable));
            }
        }).flatMap(new YResponseFunctionOriginal<>());
    }

    /**
     * 返回value为数组的时候用到，用此方法最终会返回YResponse<T>中的List<T>,剥壳壳
     */
    public static <T> ObservableTransformer<YResponseArray<T>, List<T>> handleResultArray() {
        return upstream -> upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends YResponseArray<T>>>() {
            @Override
            public ObservableSource<? extends YResponseArray<T>> apply(Throwable throwable) throws Exception {
                return Observable.error(exceptionHandle(throwable));
            }
        }).flatMap(new YResponseFunctionArray<>());
    }

    /**
     * 返回value为数组的时候用到，用此方法最终会返回YResponse<T>,原样
     */
    public static <T> ObservableTransformer<YResponseArray<T>, YResponseArray<T>> handleResultArrayOriginal() {
        return upstream -> upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends YResponseArray<T>>>() {
            @Override
            public ObservableSource<? extends YResponseArray<T>> apply(Throwable throwable) throws Exception {
                return Observable.error(exceptionHandle(throwable));
            }
        }).flatMap(new YResponseFunctionArrayOriginal<>());
    }


    /**
     * 服务其返回的数据解析 正常服务器返回数据和服务器可能返回的exception,剥壳壳
     */
    private static class YResponseFunction<T> implements
            Function<YResponse<T>, ObservableSource<T>> {
        @Override
        public ObservableSource<T> apply(YResponse<T> tYResponse) {
            String code = tYResponse.getCode();
            if (code.equals(Constants.RESPONSE_SUCCESS)) {
                if (tYResponse.getData() == null || tYResponse.getData().equals("null")) {
                    return Observable.error(new Exception("异常：code=" + code));
                } else {
                    return Observable.just(tYResponse.getData());
                }
            } else {
                return Observable.error(new Exception("异常：code=" + code + "," + tYResponse.getMessage()));
            }
        }
    }


    /**
     * 服务其返回的数据解析 正常服务器返回数据和服务器可能返回的exception,原样
     */
    private static class YResponseFunctionOriginal<T> implements Function<YResponse<T>, Observable<YResponse<T>>> {
        @Override
        public Observable<YResponse<T>> apply(YResponse<T> tYResponse) {
            String code = tYResponse.getCode();
            if (code.equals(Constants.RESPONSE_SUCCESS)) {
                return Observable.just(tYResponse);
            } else {
                return Observable.error(new Exception("异常：code=" + code + "," + tYResponse.getMessage()));
            }
        }
    }

    /**
     * 返回value为数组的时候用到,剥壳壳
     */
    private static class YResponseFunctionArray<T> implements
            Function<YResponseArray<T>, ObservableSource<List<T>>> {
        @Override
        public ObservableSource<List<T>> apply(YResponseArray<T> tYResponse) {
            String code = tYResponse.getCode();
            if (code.equals(Constants.RESPONSE_SUCCESS)) {
                if (tYResponse.getData() == null || tYResponse.getData().equals("null")) {
                    return Observable.error(new Exception("异常：code=" + code));
                } else {
                    return Observable.just(tYResponse.getData());
                }
            } else {
                return Observable.error(new Exception("异常：code=" + code + "," + tYResponse.getMessage()));
            }
        }
    }

    /**
     * 返回value为数组的时候用到,原样
     */
    private static class YResponseFunctionArrayOriginal<T> implements Function<YResponseArray<T>, Observable<YResponseArray<T>>> {
        @Override
        public Observable<YResponseArray<T>> apply(YResponseArray<T> tYResponse) {
            String code = tYResponse.getCode();
            if (code.equals(Constants.RESPONSE_SUCCESS)) {
                return Observable.just(tYResponse);
            } else {
                return Observable.error(new Exception("异常：code=" + code + "," + tYResponse.getMessage()));
            }
        }
    }

    /**
     * 异常分类
     *
     * @param throwable 异常
     * @return Throwable
     */
    private static Throwable exceptionHandle(Throwable throwable) {
        Log.e("解析错误", "异常提示" + throwable);
        if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {
            return new Throwable("解析异常");
        } else if (throwable instanceof ConnectException) {
            //网络错误
            return new Throwable("网络异常");
        } else if (throwable instanceof UnknownHostException) {
            //未知主机异常
            return new Throwable("未知主机异常");
        } else if (throwable instanceof SocketTimeoutException) {
            //连接超时
            return new Throwable("连接超时");
        } else if (throwable instanceof HttpException) {
            //Http异常
            return new Throwable(throwable.getMessage());
        } else {
            //未知错误
            return new Throwable("未知异常");
        }
    }
}