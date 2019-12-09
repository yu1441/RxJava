package com.yujing.rxjava.contract;

/**
 * RxBus发送类型封装
 * @author yujing 2019年11月8日10:23:07
 * @param <T> 泛类
 */
public class RxBusMessage<T> {
    /**
     * 类型
     */
    String type;
    /**
     * 数据
     */
    T object;

    public RxBusMessage() {

    }
    public RxBusMessage(String type, T object) {
        this.type = type;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "RxBusMessage{" +
                "type='" + type + '\'' +
                ", object=" + object +
                '}';
    }
}