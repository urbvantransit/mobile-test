package com.omargtz.mobiletest.data.remote.geocoding

import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class GeocodingDataSourceImp(private val geocodingApi:GoogleGeocodingApi ):GeocodingDataSource {

    override fun getDirection(lat: Double, lng: Double):Observable<GeocodingResponse>{
        val latLng = StringBuilder().append(lat).append(",").append(lng).toString()
        return geocodingApi.getDirection(latLng)
    }
}

