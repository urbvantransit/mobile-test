package com.example.urbvanmobiletest.modules.transmitter.presenter

import com.example.urbvanmobiletest.models.MapLocation
import com.example.urbvanmobiletest.modules.transmitter.views.TransmitterFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransmitterPresenter (val view: TransmitterFragment, val delegate: OnEventInTransmitter){

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private val DB_ROOT_NODE = "Locations"

    fun initFirebase(){
        view.context?.let {
            FirebaseApp.initializeApp(it)
        }
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference
    }

    fun sendLocationToDataBase(location: MapLocation){
        databaseReference.child(DB_ROOT_NODE).child(location.id).setValue(location)
        delegate.onLocationSended()
    }

    interface OnEventInTransmitter{
        fun onLocationSended()
    }
}