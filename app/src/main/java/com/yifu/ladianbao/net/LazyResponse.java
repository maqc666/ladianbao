package com.yifu.ladianbao.net;

import java.io.Serializable;



public class LazyResponse<T> implements Serializable {
    public int status;
    public String info;
    public T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }
}