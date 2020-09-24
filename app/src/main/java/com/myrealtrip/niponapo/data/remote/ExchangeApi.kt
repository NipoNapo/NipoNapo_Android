package com.myrealtrip.niponapo.data.remote

import com.myrealtrip.niponapo.data.model.ExchangeInfo
import com.myrealtrip.niponapo.data.model.SimpleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeApi {

    @PUT("/swap/cancel/{post_pid}")
    fun cancelExchange(@Path("post_pid") pid: Int) : Single<SimpleResponse>

    @PUT("/swap/accept/{post_pid}")
    fun acceptExchange(@Path("post_pid") pid: Int) : Single<SimpleResponse>

    @GET("/swap/request")
    fun requestExchange(@Query("post_pid") i: Int) : Single<SimpleResponse>

    @GET("/swap/list")
    fun getExchagngeInfo(@Query("state") i: Int) : Single<List<ExchangeInfo>>
}