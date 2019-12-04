package com.clwater.likelyhttp;

import com.clwater.library.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gzb on 2019/11/25.
 */
public interface BaseNetApi {

    @GET("clwater")
    Observable<BaseEntity<String>> getHome();

}
