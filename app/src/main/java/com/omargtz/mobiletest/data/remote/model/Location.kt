package com.omargtz.mobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("Lat") var lat: Double,
    @SerializedName("lng") var lng: Double
)
