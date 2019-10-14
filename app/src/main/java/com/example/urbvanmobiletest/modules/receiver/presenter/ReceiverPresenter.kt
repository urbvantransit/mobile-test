package com.example.urbvanmobiletest.modules.receiver.presenter

import android.os.CountDownTimer
import com.example.urbvanmobiletest.models.MapLocation
import com.example.urbvanmobiletest.modules.receiver.views.ReceiverFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class ReceiverPresenter(val view: ReceiverFragment, val delegate: OnEventInReceiver) {

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private var locations = ArrayList<MapLocation>()
    private var activeLocations = ArrayList<MapLocation>()

    private var countDownTimer: CountDownTimer? = null

    private val DB_ROOT_NODE = "Locations"
    private val ID_KEY = "id"
    private val LATITUDE_KEY = "latitude"
    private val LONGITUDE_KEY = "longitude"

    private val TIME = 100000L

    fun initFirebase(){
        view.context?.let {
            FirebaseApp.initializeApp(it)
        }
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference

        setLocationListener()
    }

    fun initTimeCounter(){
        if (countDownTimer == null){
            countDownTimer = object : CountDownTimer(TIME, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    for (location in locations){
                        if (location.timeLeft == 0){
                            deleteLocationFromDatabase(location.id)
                            activeLocations.remove(location)
                        }
                        else{
                            location.timeLeft -= 1000
                            if (!activeLocations.contains(location)){
                                activeLocations.add(location)
                            }
                        }
                        delegate.onChangeInLocations(activeLocations)
                    }
                }

                override fun onFinish() {
                    initTimeCounter()
                }
            }
        }
        countDownTimer?.start()
    }

    private fun setLocationListener(){
        databaseReference.child(DB_ROOT_NODE).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(data: DataSnapshot) {
                for (item in data.children){
                    addLocation(item)
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                delegate.onError()
            }
        })
    }

    private fun addLocation(item: DataSnapshot){
        val location = MapLocation()
        location.id = item.child(ID_KEY).value as String
        location.latitude = item.child(LATITUDE_KEY).value as String
        location.longitude = item.child(LONGITUDE_KEY).value as String
        locations.add(location)
    }

    private fun deleteLocationFromDatabase(id: String){
        databaseReference.child(DB_ROOT_NODE).child(id).removeValue()
    }

    interface OnEventInReceiver{
        fun onChangeInLocations(locations: ArrayList<MapLocation>)
        fun onError()
    }
}