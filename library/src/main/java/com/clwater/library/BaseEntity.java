package com.clwater.library;

/**
 * Created by gzb on 2019/11/14.
 */
public class BaseEntity<T> {
    public static int SUCCESS_CODE = 200;
    private int status;
    private String msg;
    private T data;


    public boolean isSuccess(){
        return getCode() == SUCCESS_CODE;
    }
    public int getCode() {
        return status;
    }

    public void setCode(int code) {
        this.status = code;
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
