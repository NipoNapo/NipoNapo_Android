package com.example.niponapo_3

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

//input

interface Userinfo{

    @FormUrlEncoded //인코딩 이상하게 되는 문제 해결

    @PUT("/swap/list?state=i")
    fun requestUserinfo(

        //input 정의

        @Field("id") id:String,
        @Field("pw") pw:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("idol_pid") idol_pid:Int,
        @Field("how") how:String
    )   : Call<User> //output 정의

}