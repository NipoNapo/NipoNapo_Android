package com.myrealtrip.niponapo.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ExchangeInfo(
    @SerializedName("post_pid")
    val pid: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("how")
    val how: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("state")
    var state: String,
    @SerializedName("photo")
    var photo: String
)