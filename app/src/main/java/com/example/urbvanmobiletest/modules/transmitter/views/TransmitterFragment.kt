package com.example.urbvanmobiletest.modules.transmitter.views


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.example.urbvanmobiletest.R
import com.example.urbvanmobiletest.utils.AlertUtils
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class TransmitterFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_transmitter, container, false)

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

        mMap?.setOnMapLongClickListener(this)
        mMap?.setOnMapClickListener(this)
        val myPlace = LatLng(19.416058, -99.163625)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace,15f))
    }

    override fun onMapClick(position: LatLng?) {
        context?.let {
            AlertUtils.makeToast(it,getString(R.string.keep_press_to_send))
        }
    }

    override fun onMapLongClick(p0: LatLng?) {
        context?.let {
            AlertUtils.makeToast(it,"yastuvo ferras")
        }
    }
}
