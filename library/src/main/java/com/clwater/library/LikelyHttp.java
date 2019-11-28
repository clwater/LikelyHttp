package com.clwater.library;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gzb
 * @date 2019/11/25
 */
public class LikelyHttp<T> {

    private volatile static LikelyHttp likelyHttp;

    private LikelyHttp() {
    }


    public static LikelyHttp getInstance() {
        if (likelyHttp == null) {
            synchronized (LikelyHttp.class) {
                if (likelyHttp == null) {
                    likelyHttp = new LikelyHttp();
                }
            }
        }
        return likelyHttp;
    }

    /**
     * @param successCode
     * @return
     * 设置服务端数据返回成功的状态码
     * 默认是200
     */
    public LikelyHttp setSuccessCode(int successCode) {
        BaseEntity.SUCCESS_CODE = successCode;
        return this;
    }

    /**
     * @param baseObserverInterface
     * @return
     * 设置网络情况统一处理
     * 在请求开始和结束时触发(包含成功和失败)
     */
    public LikelyHttp setUniteDeal(BaseObserverInterface baseObserverInterface){
        BaseObserver.setBaseObserverInterface(baseObserverInterface);
        return this;
    }


    /**
     * @param observable
     * @param subscriber
     * @param inIO 是否将回调执行放入io线程中
     * 执行请求
     */
    public void start(Observable observable, BaseObserver<T> subscriber, boolean inIO){
        if (inIO){
            toSubscribeIo(observable).subscribe(subscriber);
        }else {
            toSubscribeMain(observable).subscribe(subscriber);
        }
    }

    public void start(Observable observable, BaseObserver<T> subscriber){
        start(observable, subscriber, false);
    }



    /**
     * @param obs
     * @return
     * 将返回执行放入主线程中
     */
    Observable toSubscribeMain(Observable obs) {
        return obs.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * @param obs
     * @return
     * 将返回执行放入io线程中
     */
    Observable toSubscribeIo(Observable obs) {
        return obs.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

}
