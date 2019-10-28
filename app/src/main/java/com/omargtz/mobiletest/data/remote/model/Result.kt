package com.omargtz.mobiletest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("address_components") var addressComponents: List<AddressComponent>?,
    @SerializedName("formatted_address") var formattedAddress: String?,
    @SerializedName("geometry") var geometry: Geometry?,
    @SerializedName("place_id") var placeId: String?,
    @SerializedName("plus_code") var plusCode: PlusCode?,
    @SerializedName("types") var types: List<String>?
)