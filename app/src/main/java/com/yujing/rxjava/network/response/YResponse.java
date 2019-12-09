package com.yujing.rxjava.network.response;

import java.io.Serializable;

/**
 * <p>
 * 返回结果封装类
 * </p>
 */
public class YResponse<T> implements Serializable {

  private String code;
  private String message;
  private T data;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
