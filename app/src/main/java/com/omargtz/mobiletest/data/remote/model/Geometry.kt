package com.omargtz.mobiletest.data.remote.model

import android.view.View

import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("location") var location: Location?,
    @SerializedName("location_type") var locationType: String?,
    @SerializedName("viewport") var viewPort: ViewPort?
)

