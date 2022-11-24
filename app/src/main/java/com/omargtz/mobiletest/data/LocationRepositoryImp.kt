package com.omargtz.mobiletest.data

import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource
import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import io.reactivex.Observable

class LocationRepositoryImp(val geocodingDataSource: GeocodingDataSource, val firebaseDatasource: FirebaseDbDataSource ):LocationRepository{

    override fun loadLocations(onGetLocations: FirebaseDbDataSource.OnGetLocations) {
        firebaseDatasource.receiverLocations(onGetLocations)
    }

    override fun loadDirection(lat: Double,lng:Double): Observable<GeocodingResponse> {
        return geocodingDataSource.getDirection(lat,lng)
    }

    override fun sendLocation(locationDTO: LocationDTO) {
        firebaseDatasource.sendLocation(locationDTO)
    }
}



