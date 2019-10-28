package com.omargtz.mobiletest.data.remote.firebase.model

data class LocationRequest(
    var lat: Double? = 0.0,
    var lng: Double?= 0.0,
    var direction: String? = "",
    var dateTime: Long = 0)