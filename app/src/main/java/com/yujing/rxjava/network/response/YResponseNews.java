package com.yujing.rxjava.network.response;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 返回结果封装类
 * </p>
 */
public class YResponseNews<T> implements Serializable {

  private String code;
  private String message;
  private List<T> result;

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

  public List<T> getResult() {
    return result;
  }
  public void setResult(List<T> result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "YResponseNews{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", result=" + result +
            '}';
  }
}
