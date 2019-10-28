package com.omargtz.mobiletest.data.remote.geocoding

import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class GeocodingDataSourceImp(private val geocodingApi:GoogleGeocodingApi ):GeocodingDataSource {

    override fun getDirection(lat: Double, lng: Double, onGeocodingLocation: GeocodingDataSource.OnGeocodingLocation){

        val latLng = StringBuilder().append(lat).append(",").append(lng).toString();

        geocodingApi.getDirection(latLng).enqueue(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                if(response.code()==200){
                    onGeocodingLocation.onSucess(response.body()!!)
                }else{
                    onGeocodingLocation.onError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                onGeocodingLocation.onErrorConnection()
            }

        })
    }
}

