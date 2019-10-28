package com.omargtz.mobiletest.data.remote.firebase

import com.google.firebase.database.*
import com.omargtz.mobiletest.data.remote.firebase.model.LocationRequest

class FirebaseDbDataSourceImp(val dbReference: DatabaseReference) :FirebaseDbDataSource{

    private val CHILD = "locationRequest"

    override fun receiverLocations(onGetLocations: FirebaseDbDataSource.OnGetLocations) {
        val locationListener = object : ValueEventListener{
            override fun onCancelled(data: DatabaseError) {
                onGetLocations.onError()
            }
            override fun onDataChange(data: DataSnapshot) {
                var locations = ArrayList<LocationRequest>()
                for (data in data.children){
                    var location = data.getValue(LocationRequest::class.java)
                    locations.add(location!!)
                }


                if(locations.isEmpty()){
                    onGetLocations.onEmpty()
                }else{
                    onGetLocations.onSucess(locations)
                }
            }
        }
        dbReference.child(CHILD).addValueEventListener(locationListener)
    }

    override fun sendLocation(location: LocationRequest) {
        dbReference.child(CHILD).setValue(location)
    }

}