package com.example;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private java.lang.String msg;
    private T data;

    public Result() {

    }

    public Result(Integer code, java.lang.String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public java.lang.String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
