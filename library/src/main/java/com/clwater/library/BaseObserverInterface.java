package com.clwater.library;

/**
 * Created by gzb on 2019/11/28.
 */
public interface BaseObserverInterface {
    //请求开始
    void onRequestStart();
    //请求完成
    void onRequestEnd();
    //请求异常
    void onCodeError(int errorCode, String errorMsg);
}
