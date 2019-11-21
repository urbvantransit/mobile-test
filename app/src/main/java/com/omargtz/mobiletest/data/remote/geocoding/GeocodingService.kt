package com.omargtz.mobiletest.data.remote.geocoding


import com.google.gson.Gson
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

object GeocodingService {

    val geocodingServiceProvider: GoogleGeocodingApi
    get() = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GoogleGeocodingApi::class.java)

}
