package com.omargtz.mobiletest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GeocodingResponse(
    @SerializedName("plus_code")var plusCode: PlusCode?,
    @SerializedName("results") var results: List<Result>?,
    @SerializedName("status") var status:String?
)
