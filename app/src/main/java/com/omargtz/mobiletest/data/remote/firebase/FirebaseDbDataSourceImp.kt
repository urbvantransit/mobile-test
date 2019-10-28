package com.omargtz.mobiletest.data.remote.firebase

import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO

class FirebaseDbDataSourceImp(val dbReference: DatabaseReference) :FirebaseDbDataSource{

    private val CHILD = "locations"

    override fun receiverLocations(onGetLocations: FirebaseDbDataSource.OnGetLocations) {
        val locationListener = object : ValueEventListener{
            override fun onCancelled(data: DatabaseError) {
                onGetLocations.onError()
            }
            override fun onDataChange(data: DataSnapshot) {
                val locations = ArrayList<LocationDTO>()
                for (data in data.children){
                    val location = data.getValue(LocationDTO::class.java)
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

    override fun sendLocation(location: LocationDTO) {
        dbReference.child(CHILD).child(location.dateTime.toString()).setValue(location)

    }
}


