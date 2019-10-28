package com.omargtz.mobiletest.data.remote.firebase.model

data class LocationRequest(
    val lat: Double? = 0.0,
    val lng: Double?= 0.0,
    val direction: String? = "",
    val dateTime: Long = 0){

}