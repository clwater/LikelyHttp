package com.clwater.library;

import android.accounts.NetworkErrorException;
import android.content.Context;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gzb on 2019/11/14.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    /*
     * 是否经过统一处理, 默认均使用
     */
    private boolean userUniteDeal = true;

    private static BaseObserverInterface baseObserverInterface;

    public static void setBaseObserverInterface(BaseObserverInterface baseObserverInterface) {
        BaseObserver.baseObserverInterface = baseObserverInterface;
    }

    public BaseObserver() {

    }

    public BaseObserver(boolean useLoading) {
        this.userUniteDeal = useLoading;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * @param t
     * @throws Exception
     * 网络请求成功, 状态码错误
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
        baseObserverInterface.onCodeError(t.getCode());
    }


    /**
     * @param t
     * @throws Exception
     * 请求成功(网络及状态码code)
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     * 网络请求失败
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;


    /**
     * 网络请求开始
     */
    protected void onRequestStart() {
        if (userUniteDeal) {
            baseObserverInterface.onRequestStart();
        }
    }

    /**
     * 网络请求完成(包括成功及失败)
     */
    protected void onRequestEnd() {
        if (userUniteDeal) {
            baseObserverInterface.onRequestEnd();
        }
    }


}
