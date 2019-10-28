package com.omargtz.mobiletest.location.viewmodel

import android.content.Context
import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omargtz.mobiletest.data.LocationRepository
import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.model.LocationRequest
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource
import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import com.omargtz.mobiletest.utils.Event
import java.io.ObjectStreamException

class LocationViewModel(val locationRepository: LocationRepository): ViewModel() {

    private val _mLocations = MutableLiveData<List<LocationRequest>>().apply { value = emptyList() }
    val mLocations: LiveData<List<LocationRequest>> = _mLocations


    private val _locationErrorConnectionEvent = MutableLiveData<Event<String>>()
    val locationErrorConnectionEvent: LiveData<Event<String>> = _locationErrorConnectionEvent


    private val _locationErrorEvent = MutableLiveData<Event<String>>()
    val locationErrorEvent: LiveData<Event<String>> = _locationErrorEvent

    private val _mDirection = MutableLiveData<GeocodingResponse>()
    val mDirection : LiveData<GeocodingResponse> = _mDirection

    private val _directionErrorConnectionEvent = MutableLiveData<Event<String>>()
    val direcctionErrorConnectionEvent: LiveData<Event<String>> = _directionErrorConnectionEvent

    private val _directionErrorEvent = MutableLiveData<Event<String>>()
    val directionErrorEvent: LiveData<Event<String>> = _directionErrorEvent

    fun sendLocation(lat: Double, lng: Double, direction: String ){
        val locationRequest = LocationRequest(lat,lng,direction,System.currentTimeMillis());
        locationRepository.sendLocation(locationRequest)
    }

    fun loadLocations(){
        locationRepository.loadLocations(object : FirebaseDbDataSource.OnGetLocations  {

            override fun onSucess(locations: List<LocationRequest>) {
                _mLocations.value = locations
            }

            override fun onEmpty() {
                _locationErrorEvent.value = Event("NO hay ubicaciones")
            }

            override fun onError() {
                _locationErrorConnectionEvent.value = Event("Error de conexión")
            }
        })
    }

    fun loadDirection(lat:Double,lng: Double){
        locationRepository.loadDirection(lat,lng,object : GeocodingDataSource.OnGeocodingLocation{
            override fun onSucess(direction: GeocodingResponse) {
                _mDirection.value = direction
            }

            override fun onError(error: String) {
                _locationErrorEvent.value = Event("Error al obtener dirección")
            }

            override fun onErrorConnection() {
                _locationErrorConnectionEvent.value = Event("Error de conexión")
            }

        })

    }
}