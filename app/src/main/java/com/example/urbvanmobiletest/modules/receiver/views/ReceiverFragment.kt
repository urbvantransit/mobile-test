package com.example.urbvanmobiletest.modules.receiver.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.urbvanmobiletest.R
import com.example.urbvanmobiletest.models.MapLocation
import com.example.urbvanmobiletest.modules.receiver.presenter.ReceiverPresenter
import com.example.urbvanmobiletest.utils.AlertUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class ReceiverFragment : Fragment(), OnMapReadyCallback, ReceiverPresenter.OnEventInReceiver {

    private lateinit var presenter: ReceiverPresenter

    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null

    val DEFAULT_LATITUDE = 19.416058
    val DEFAULT_LONGITUDE = -99.163625
    val DEFAULT_ZOOM = 15f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_receiver, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ReceiverPresenter(this, this)

        presenter.initFirebase()

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

    override fun onLocationGetted(location: MapLocation) {
        val latLng = LatLng(location.latitude.toDouble(),location.longitude.toDouble())
        val markerOptions = MarkerOptions().position(latLng)
        mMap?.addMarker(markerOptions)
    }

    override fun onError() {
        context?.let { AlertUtils.makeToast(it,it.getString(R.string.error_message)) }
    }
}
