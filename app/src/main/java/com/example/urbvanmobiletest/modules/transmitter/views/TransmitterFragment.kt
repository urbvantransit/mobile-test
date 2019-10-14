package com.example.urbvanmobiletest.modules.transmitter.views


import android.content.Context
import android.os.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.example.urbvanmobiletest.R
import com.example.urbvanmobiletest.models.MapLocation
import com.example.urbvanmobiletest.modules.transmitter.presenter.TransmitterPresenter
import com.example.urbvanmobiletest.utils.AlertUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import java.util.*

class TransmitterFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, TransmitterPresenter.OnEventInTransmitter {

    private lateinit var presenter: TransmitterPresenter

    private lateinit var mapView: MapView
    private var mMap: GoogleMap? = null

    val DEFAULT_LATITUDE = 19.416058
    val DEFAULT_LONGITUDE = -99.163625
    val DEFAULT_ZOOM = 15f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_transmitter, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TransmitterPresenter(this, this)

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

        mMap?.setOnMapLongClickListener(this)
        mMap?.setOnMapClickListener(this)

        val myPlace = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace,DEFAULT_ZOOM))
    }

    override fun onMapClick(position: LatLng?) {
        context?.let {
            AlertUtils.makeToast(it,getString(R.string.keep_press_to_send))
        }
    }

    override fun onMapLongClick(position: LatLng?) {
        vibratePhone()
        val location = MapLocation(UUID.randomUUID().toString(),position?.latitude.toString(),position?.longitude.toString())
        presenter.sendLocationToDataBase(location)
    }

    override fun onLocationSended() {
        context?.let {
            AlertUtils.makeToast(it,getString(R.string.location_sended))
        }
    }

    private fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}
