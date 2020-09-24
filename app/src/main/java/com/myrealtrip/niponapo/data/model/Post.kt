package com.myrealtrip.niponapo.data.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    val id: String,
    @SerializedName("how")
    val how: String,
    @SerializedName("album_pid")
    val albumPid: Int,
    @SerializedName("have")
    var have: Int,
    @SerializedName("want")
    var want: Int,
    @SerializedName("photo")
    val photo: String
)