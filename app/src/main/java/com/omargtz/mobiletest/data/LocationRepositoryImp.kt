package com.omargtz.mobiletest.data

import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.model.LocationRequest
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource

class LocationRepositoryImp(val geocodingDataSource: GeocodingDataSource, val firebaseDatasource: FirebaseDbDataSource ):LocationRepository{

    override fun loadLocations(onGetLocations: FirebaseDbDataSource.OnGetLocations) {
        firebaseDatasource.receiverLocations(onGetLocations)
    }

    override fun loadDirection(lat: Double,lng:Double, onGeocodingLocation: GeocodingDataSource.OnGeocodingLocation) {
        geocodingDataSource.getDirection(lat,lng,onGeocodingLocation)
    }

    override fun sendLocation(locationRequest: LocationRequest) {
        firebaseDatasource.sendLocation(locationRequest)
    }
}



