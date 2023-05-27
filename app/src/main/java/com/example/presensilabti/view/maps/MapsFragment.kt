package com.example.presensilabti.view.maps

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.presensilabti.R
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val centerLat = -6.3538282
    private val centerLng = 106.8412196
    private val geofenceRadius = 20.0
    private lateinit var nMap : GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->

        nMap = googleMap
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val labti = LatLng(centerLat, centerLng)
        googleMap.addMarker(MarkerOptions().position(labti).title("Laboratorium Teknik Informatika"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(labti, 20f))

        googleMap.addCircle(
            CircleOptions()
                .center(labti)
                .radius(geofenceRadius)
                .fillColor(0x22FF0000)
                .strokeColor(Color.RED)
                .strokeWidth(3f)
        )

        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        getMyLocation(googleMap)

    }

    val requestBackgroundLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted : Boolean ->
            if (isGranted){
                getMyLocation(nMap)
            }
        }

    private val runningQrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @TargetApi(Build.VERSION_CODES.Q)
    val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted : Boolean ->
            if (isGranted){
                if (runningQrLater){
                    requestBackgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                } else{
                    getMyLocation(nMap)
                }
            }
        }

    fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.Q)
    fun checkForegroundAndBackgroundLocationPermission(): Boolean {
        val foregroundLocationAproved = checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        val backgroundPermissionAproved =
            if (runningQrLater){
                checkPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            } else{
                true
            }
        return foregroundLocationAproved && backgroundPermissionAproved

    }


    @SuppressLint("MissingPermission")
    fun getMyLocation(nMap: GoogleMap) {
        if (checkForegroundAndBackgroundLocationPermission()){
            nMap.isMyLocationEnabled = true
        } else {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}