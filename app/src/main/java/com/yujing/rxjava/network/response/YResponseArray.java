package com.yujing.rxjava.network.response;

import java.io.Serializable;
import java.util.List;

/**
 *返回结果封装类
 * @author yujing 2019年11月8日11:26:49
 */

public class YResponseArray<T> implements Serializable {

  private String code;
  private String message;
  private List<T> data;

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

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
