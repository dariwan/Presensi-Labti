package com.example.presensilabti.view.absen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat


class GetLocation(private val context: Context) : LocationListener {

    fun getLocation(): Location? {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show()
            return null
        }

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                1f,
                this
            )
            return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        } else if (isGpsEnabled) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1f,
                this
            )
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            Toast.makeText(context, "Please Enable GPS", Toast.LENGTH_SHORT).show()
        }

        return null
    }

    override fun onLocationChanged(location: Location) {
        // Implementasikan sesuai kebutuhan Anda
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // Implementasikan sesuai kebutuhan Anda
    }

    override fun onProviderEnabled(provider: String) {
        // Implementasikan sesuai kebutuhan Anda
    }

    override fun onProviderDisabled(provider: String) {
        // Implementasikan sesuai kebutuhan Anda
    }
}

