package com.example.urbvanmobiletest.modules.receiver.presenter

import com.example.urbvanmobiletest.models.MapLocation
import com.example.urbvanmobiletest.modules.receiver.views.ReceiverFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class ReceiverPresenter(val view: ReceiverFragment, val delegate: OnEventInReceiver) {

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private val DB_ROOT_NODE = "Locations"
    private val ID_KEY = "id"
    private val LATITUDE_KEY = "latitude"
    private val LONGITUDE_KEY = "longitude"

    fun initFirebase(){
        view.context?.let {
            FirebaseApp.initializeApp(it)
        }
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference

        setLocationListener()
    }

    private fun setLocationListener(){
        databaseReference.child(DB_ROOT_NODE).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(data: DataSnapshot) {
                for (item in data.children){
                    val location = MapLocation()
                    location.id = item.child(ID_KEY).value as String
                    location.latitude = item.child(LATITUDE_KEY).value as String
                    location.longitude = item.child(LONGITUDE_KEY).value as String
                    delegate.onLocationGetted(location)
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                delegate.onError()
            }
        })
    }

    interface OnEventInReceiver{
        fun onLocationGetted(location: MapLocation)
        fun onError()
    }
}