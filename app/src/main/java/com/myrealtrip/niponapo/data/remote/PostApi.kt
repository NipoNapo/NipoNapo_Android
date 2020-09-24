package com.myrealtrip.niponapo.data.remote

import com.myrealtrip.niponapo.data.model.Post
import com.myrealtrip.niponapo.data.model.SimpleResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {

    @DELETE("/post/delete/{post_pid}")
    fun deletePost(@Path("post_pid") pid: Int) : Single<SimpleResponse>

    @POST("/post/create")
    fun createPost(@Body post: Post) : Single<SimpleResponse>
}