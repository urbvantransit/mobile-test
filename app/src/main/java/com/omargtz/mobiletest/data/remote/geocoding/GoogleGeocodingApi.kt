package com.omargtz.mobiletest.data.remote.geocoding

import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GoogleGeocodingApi {

    @GET("api/geocode/json?key=AIzaSyBHHlRJ8j6ME__Y4oPv9zx1OLKgH48SQFs")
    fun getDirection(@Query("latlng") latlng: String): Observable<GeocodingResponse>

}