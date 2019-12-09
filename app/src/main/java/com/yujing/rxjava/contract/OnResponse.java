package com.yujing.rxjava.contract;

/**
 * OnResponse 网络请求消息回复
 *
 * @author yujing 2019年11月8日10:28:06
 */
public interface OnResponse {
    /**
     * 失败
     *
     * @param errorMsg 错误类型
     */
    void onFail(String errorMsg);

    /**
     * 成功
     *
     * @param type   类型
     * @param object 数据
     */
    void onSuccess(String type, Object object);
}