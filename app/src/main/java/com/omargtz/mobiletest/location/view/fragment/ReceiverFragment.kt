package com.omargtz.mobiletest.location.view.fragment


import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.omargtz.mobiletest.R
import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO
import com.omargtz.mobiletest.location.view.adapter.LocationsAdapter
import com.omargtz.mobiletest.location.viewmodel.LocationViewModel
import com.omargtz.mobiletest.utils.Event
import kotlinx.android.synthetic.*

class ReceiverFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null
    private lateinit var viewmodel: LocationViewModel
    private lateinit var listOfLocations: RecyclerView
    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.receiver_map_view)
        listOfLocations = view.findViewById(R.id.list_of_locations)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel = ViewModelProviders.of(activity!!)[LocationViewModel::class.java]
        setupListLocations()
        subscribeLocationsError()
        subscribeLoadLocations()
        subscribeClickItemLocationEvent()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()

    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        viewmodel.loadLocations()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        initMap()
    }

    private fun initMap(){
        mMap!!.isMyLocationEnabled = true
        mMap!!.uiSettings.isMyLocationButtonEnabled = true;
        mMap!!.uiSettings.isCompassEnabled = true
    }

    private fun moveCamera(lat: Double, lng: Double ){
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(lat,lng),15.0f)
        mMap!!.animateCamera(cameraUpdate)
    }

    fun  addMarkers( locations: List<LocationDTO>) {
        for (locationDTO in locations){
            val latLng = LatLng(locationDTO.lat!!,locationDTO.lng!!)
             mMap!!.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("marker")
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        }
    }

    private fun setupListLocations(){
        locationsAdapter = LocationsAdapter(viewmodel.mLocations.value!!,viewmodel)
        listOfLocations.adapter = locationsAdapter;
        listOfLocations.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL,false)

    }

    private fun subscribeLoadLocations(){
        viewmodel.mLocations.observe(this, object : Observer<List<LocationDTO>>{
            override fun onChanged(locations: List<LocationDTO>?) {
                if(locations!=null){
                    locationsAdapter.updateList(locations)
                    addMarkers(locations)
                }
            }
        })
    }

    private fun subscribeLocationsError(){
       viewmodel.locationErrorEvent.observe(this,object : Observer<Event<String>>{
           override fun onChanged(event: Event<String>?) {
               if(event!!.getContentIfNotHandled()!=null){
                   Log.e("Locations","ERROR")
               }
           }
       })
    }

    private fun subscribeClickItemLocationEvent(){
        viewmodel.clickLocatinEvent.observe(this,object : Observer<Event<LocationDTO>>{
            override fun onChanged(event: Event<LocationDTO>?) {
                val locationItem = event!!.getContentIfNotHandled();
                if(locationItem!=null){
                    moveCamera(locationItem.lat!!,locationItem.lng!!)
                }
            }
        })
    }
}
