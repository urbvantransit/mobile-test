package com.omargtz.mobiletest.data.remote.geocoding


import com.google.gson.Gson
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingService {

    val geocodingServiceProvider: GoogleGeocodingApi
    get() = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GoogleGeocodingApi::class.java)

}
