package com.example.urbvanmobiletest.modules.receiver.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.urbvanmobiletest.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class ReceiverFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null

    val DEFAULT_LATITUDE = 19.416058
    val DEFAULT_LONGITUDE = -99.163625
    val DEFAULT_ZOOM = 15f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view =inflater.inflate(R.layout.fragment_receiver, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
    }

    private fun initMap(){
        mapView = view?.findViewById(R.id.map_view) as MapView

        mapView.onCreate(null)
        mapView.onResume()
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
        val myPlace = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace,DEFAULT_ZOOM))
    }
}
