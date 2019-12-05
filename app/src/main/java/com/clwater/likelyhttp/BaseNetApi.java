package com.clwater.likelyhttp;

import com.clwater.library.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gzb on 2019/11/25.
 */
public interface BaseNetApi {

    @GET("getSuccess")
    Observable<BaseEntity<Object>> getSuccess();

    @GET("getFail")
    Observable<BaseEntity<String>> getFail();

    @GET("getCodeFail")
    Observable<BaseEntity<String>> getCodeFail();

    @POST("postMethod")
    Observable<BaseEntity<String>> postMethod();

    @GET("returnInIO")
    Observable<BaseEntity<Object>> returnInIO();

}
