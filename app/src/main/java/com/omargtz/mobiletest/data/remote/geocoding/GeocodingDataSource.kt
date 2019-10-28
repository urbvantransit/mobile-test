package com.omargtz.mobiletest.data.remote.geocoding

import com.omargtz.mobiletest.data.remote.model.GeocodingResponse

interface GeocodingDataSource {


    interface OnGeocodingLocation {
        fun onSucess(direction: GeocodingResponse)
        fun onError(error: String)
        fun onErrorConnection()
    }

    fun getDirection(lat: Double, lng: Double, onGeocodingLocation: OnGeocodingLocation)

}