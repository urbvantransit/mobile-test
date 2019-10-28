package com.omargtz.mobiletest.data

import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.model.LocationRequest
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource

interface LocationRepository {

    fun sendLocation(locationRequest: LocationRequest)
    fun loadLocations(onGetLocations: FirebaseDbDataSource.OnGetLocations)
    fun loadDirection(lat: Double,lng: Double,onGeocodingLocation: GeocodingDataSource.OnGeocodingLocation)

}