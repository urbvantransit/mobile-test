package com.omargtz.mobiletest.location.viewmodel

import androidx.lifecycle.*
import com.omargtz.mobiletest.data.LocationRepository
import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource
import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import com.omargtz.mobiletest.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.math.E

class LocationViewModel(val locationRepository: LocationRepository): ViewModel() {

    private val _mLocations = MutableLiveData<List<LocationDTO>>().apply { value = emptyList() }
    val mLocations: LiveData<List<LocationDTO>> = _mLocations


    private val _locationErrorConnectionEvent = MutableLiveData<Event<String>>()

    private val _locationErrorEvent = MutableLiveData<Event<String>>()
    val locationErrorEvent: LiveData<Event<String>> = _locationErrorEvent

    private val _mDirection = MutableLiveData<GeocodingResponse>()
    val mDirection : LiveData<GeocodingResponse> = _mDirection

    private val _directionErrorConnectionEvent = MutableLiveData<Event<String>>()
    val directionErrorConnectionEvent: LiveData<Event<String>> = _directionErrorConnectionEvent

    private val _directionErrorEvent = MutableLiveData<Event<String>>()
    val directionErrorEvent: LiveData<Event<String>> = _directionErrorEvent

    private val _clickLocationEvent = MutableLiveData<Event<LocationDTO>>()
    val clickLocatinEvent : LiveData<Event<LocationDTO>> = _clickLocationEvent

    private val subscriptions = CompositeDisposable()


    public fun clickItemLocation(location: LocationDTO){
        _clickLocationEvent.value = Event(location)
    }

    fun sendLocation(lat: Double, lng: Double, direction: String ){
        val locationRequest = LocationDTO(lat,lng,direction,System.currentTimeMillis());
        locationRepository.sendLocation(locationRequest)
    }

    fun loadLocations(){
        locationRepository.loadLocations(object : FirebaseDbDataSource.OnGetLocations  {

            override fun onSucess(locations: List<LocationDTO>) {
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

    fun loadDirection(lat:Double,lng: Double) {
       val disposable = locationRepository.loadDirection(lat,lng)
            .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .doOnSubscribe {

           }
           .doOnTerminate {

           }
           .subscribe ({
               _mDirection.value = it
           }, {
               _locationErrorEvent.value = Event("Error al obtener dirección")
           })

        subscribe(disposable)
    }

    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        subscriptions.clear()
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }


}