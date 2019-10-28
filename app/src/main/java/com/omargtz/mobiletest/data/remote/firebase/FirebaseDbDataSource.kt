package com.omargtz.mobiletest.data.remote.firebase

import com.omargtz.mobiletest.data.remote.firebase.model.LocationRequest
import com.omargtz.mobiletest.data.remote.model.Location

interface FirebaseDbDataSource {

    interface OnGetLocations{
        fun onSucess(locations: List<LocationRequest>)
        fun onEmpty()
        fun onError()
    }



    fun sendLocation(location: LocationRequest)
    fun receiverLocations(onGetLocations: OnGetLocations)
}
