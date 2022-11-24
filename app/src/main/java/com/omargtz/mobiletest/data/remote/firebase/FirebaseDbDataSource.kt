package com.omargtz.mobiletest.data.remote.firebase

import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO

interface FirebaseDbDataSource {

    interface OnGetLocations{
        fun onSucess(locations: List<LocationDTO>)
        fun onEmpty()
        fun onError()
    }



    fun sendLocation(location: LocationDTO)
    fun receiverLocations(onGetLocations: OnGetLocations)
}
