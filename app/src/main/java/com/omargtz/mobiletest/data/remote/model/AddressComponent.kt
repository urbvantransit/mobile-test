package com.omargtz.mobiletest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class AddressComponent(
    @SerializedName("long_name") var longName: String?,
    @SerializedName("short_name") var shortName: String?,
    @SerializedName("types") var types: List<String>?
)