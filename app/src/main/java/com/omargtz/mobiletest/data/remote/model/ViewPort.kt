package com.omargtz.mobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ViewPort(
    @SerializedName("northeast") var northeast: Northeast?,
    @SerializedName("southwest") var southwest: Southwest?
)
