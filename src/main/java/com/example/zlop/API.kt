package com.example.zlop


import retrofit2.Call
import retrofit2.http.*



interface API {


    @GET("/member/idChk?id=i")
    fun getIdCheck(@Query("id") id: String): Call<idcheck>


    @GET("/member/emailChk?email=i")
    fun getEmailCheck(@Query("email") email: String): Call<emailcheck>



   @POST("/user/idsearch") // 아이디 찾기
   @FormUrlEncoded
   fun IdSearch(@Field("name") name : String, @Field("email")email : String) : Call<Void>

    @POST("/user/pwsearch") // 비번 찾기
    @FormUrlEncoded
    fun PwSearch(@Field("id") id : String, @Field("name") name : String, @Field("email")email : String) : Call<Void>


    @POST("/user/signin")  //로그인
    @FormUrlEncoded
    fun logIn(@Field("id") id : String, @Field("pw") pw : String) :  Call<Void>

    @POST("/user/signup") //회원가입
    @FormUrlEncoded
    fun logUp(@Field("name") name : String, @Field("id") id : String, @Field("pw") pw : String,@Field("email") email : String, @Field("idol_pid") idol_pid : Int, @Field("how") how : Int) :  Call<Void>
}