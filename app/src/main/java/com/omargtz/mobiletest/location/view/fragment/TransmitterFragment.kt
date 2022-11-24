package com.omargtz.mobiletest.location.view.fragment


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.omargtz.mobiletest.R
import com.omargtz.mobiletest.data.remote.model.GeocodingResponse
import com.omargtz.mobiletest.location.viewmodel.LocationViewModel
import com.omargtz.mobiletest.utils.Event

/**
 * A simple [Fragment] subclass.
 */
class TransmitterFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveListener {

    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION = 42
    private lateinit var viewmodel: LocationViewModel
    private var marker: Marker? = null
    private lateinit var btnSendLocation: Button
    private lateinit var tvDirection: TextView
    private lateinit var animationSearchLocation: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transmitter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(activity!!)[LocationViewModel::class.java]
        setLocationClient()
        subscribeLoadDirection()
        subscribeloadDirectionConnectionErrorEvent()
        subscribeloadDirectionErrorEvent()

        mapView = view.findViewById(R.id.transmiter_map_view)
        btnSendLocation = view.findViewById(R.id.btn_send_location);

        btnSendLocation.setOnClickListener(){_->
            val address =  viewmodel.mDirection.value!!.results!!.get(0).formattedAddress!!
            viewmodel.sendLocation(getLatLngMarker().latitude,getLatLngMarker().longitude,address)
            Toast.makeText(activity,"Ubicación enviada",Toast.LENGTH_SHORT).show()
        }

        tvDirection = view.findViewById(R.id.tv_direction)
        animationSearchLocation = view.findViewById(R.id.animation_search_location)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
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
        if (checkSelfPermission(context!!,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initMap()
            getLastLocation()
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION)
        }
    }

    override fun onCameraIdle() {
        viewmodel.loadDirection(getLatLngMarker().latitude,getLatLngMarker().longitude)
    }

    override fun onCameraMove() {
        addMarker(getLatLngMarker())
    }

    private fun getLatLngMarker():LatLng{
        return mMap!!.cameraPosition.target
    }

    @SuppressLint("MissingPermission")
    private fun initMap(){
        mMap!!.isMyLocationEnabled = true
        mMap!!.uiSettings.isMyLocationButtonEnabled = true;
        mMap!!.uiSettings.isCompassEnabled = true
        mMap!!.setOnCameraIdleListener(this)
        mMap!!.setOnCameraMoveListener(this)
    }

    private fun setLocationClient(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location ->
            moveCamera(location)
            viewmodel.loadDirection(location.latitude,location.longitude)
        }
    }

    private fun moveCamera(location: Location){
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),15.0f)
        mMap!!.animateCamera(cameraUpdate)
    }

     fun  addMarker(latLng: LatLng) {
             if(marker==null) {
                 marker = mMap!!.addMarker(MarkerOptions()
                    .position(latLng)
                    .title("marker")
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))

             }else {
                 marker!!.position = latLng
             }
    }

    private fun subscribeLoadDirection(){
        viewmodel.mDirection.observe(this,object : Observer<GeocodingResponse> {
            override fun onChanged(geocodingDirections: GeocodingResponse?) {
                if(!geocodingDirections!!.results!!.isEmpty()){
                    val address = geocodingDirections.results?.get(0)!!.formattedAddress;
                    tvDirection.setText(address)
                }
            }
        })
    }

    private fun subscribeloadDirectionErrorEvent(){
        viewmodel.directionErrorEvent.observe(this,
            Observer<Event<String>> { event ->
                if(event!!.getContentIfNotHandled()!=null){
                    Log.e("Direction","Error")
                }
            })
    }

    private fun subscribeloadDirectionConnectionErrorEvent(){
        viewmodel.directionErrorConnectionEvent.observe(this,
            Observer<Event<String>> { event ->
                if(event!!.getContentIfNotHandled()!=null){
                    Log.e("Direction","ErrorConnection")
                }
            })
    }
}
