package com.example.presensilabti.view.absen

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.presensilabti.R
import com.example.presensilabti.databinding.ActivityAbsenUserBinding
import com.example.presensilabti.view.maps.GeofenceBroadcastReceiver
import com.example.presensilabti.view.maps.MapsFragment
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

class AbsenUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAbsenUserBinding

    private lateinit var geofencingClient : GeofencingClient

//    private val centerLat = -6.3538282
//    private val centerLng = 106.8412196
//    private val geofenceRadius = 20.0
//
//    private val geofencePendingIntent: PendingIntent by lazy {
//        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
//        intent.action = GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
//        } else{
//            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val mMapsFragment = MapsFragment()

        val geofence = GeofenceBroadcastReceiver()

        Log.d("FragmentMaps", "Fragment Name : " + MapsFragment::class.java.simpleName)

        mFragmentManager.beginTransaction()
            .add(R.id.frame_maps, mMapsFragment)
            .commit()

        binding.btnCheckIn.setOnClickListener {
            mMapsFragment
            Toast.makeText(this, "bang udah bang", Toast.LENGTH_SHORT).show()
        }

        setupView()


    }

    private fun setupView() {
        supportActionBar?.hide()
    }

//    @SuppressLint("MissingPermission")
//    private fun addGeofence() {
//        geofencingClient = LocationServices.getGeofencingClient(this)
//
//        val geofence = Geofence.Builder()
//            .setRequestId("labti")
//            .setCircularRegion(
//                centerLat,
//                centerLng,
//                geofenceRadius.toFloat()
//            )
//
//            .setExpirationDuration(Geofence.NEVER_EXPIRE)
//            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL)
//            .setLoiteringDelay(5000)
//            .build()
//
//        val geofencingRequest = GeofencingRequest.Builder()
//            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL)
//            .addGeofence(geofence)
//            .build()
//
//        geofencingClient.removeGeofences(geofencePendingIntent).run {
//            addOnCompleteListener {
//                geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent).run {
//                    addOnSuccessListener {
//                        showToast("Geofencing added")
//                    }
//                    addOnFailureListener {
//                        showToast("Geofencing not added : ${it.message}")
//                    }
//                }
//            }
//        }
//    }
//
//    private fun showToast(text : String) {
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
//    }
}