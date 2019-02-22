package com.example.demo.demoboot2.Result;

import java.io.Serializable;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-13 15:13
 * @Description:
 */
public class ApiResult<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
