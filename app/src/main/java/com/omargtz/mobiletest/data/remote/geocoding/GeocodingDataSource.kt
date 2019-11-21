package com.omargtz.mobiletest.data.remote.geocoding

import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import io.reactivex.Observable

interface GeocodingDataSource {

    fun getDirection(lat: Double, lng: Double): Observable<GeocodingResponse>

}