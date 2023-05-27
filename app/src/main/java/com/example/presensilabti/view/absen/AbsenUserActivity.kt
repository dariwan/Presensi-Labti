package com.example.presensilabti.view.absen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.EdgeEffectCompat.getDistance
import com.example.presensilabti.R
import com.example.presensilabti.databinding.ActivityAbsenUserBinding
import com.example.presensilabti.view.maps.MapsFragment
import com.google.android.gms.maps.GoogleMap
import kotlin.math.*

class AbsenUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAbsenUserBinding
    private lateinit var googleMap: GoogleMap

    private lateinit var map: MapsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val mMapsFragment = MapsFragment()




        Log.d("FragmentMaps", "Fragment Name : " + MapsFragment::class.java.simpleName)

        mFragmentManager.beginTransaction()
            .add(R.id.frame_maps, mMapsFragment)
            .commit()

        binding.btnCheckIn.setOnClickListener {
            val getLocation = GetLocation(applicationContext)
            val location = getLocation.getLocation()
            if (location != null) {
                val latitudeSaya = location.latitude
                val longitudeSaya = location.longitude

                val jarak = isDistance(latitudeSaya, longitudeSaya)
               if (jarak < 20.0){
                   Toast.makeText(this, "Absen Berhasil", Toast.LENGTH_SHORT).show()
               } else{
                   Toast.makeText(this, "kamu belum dalam jangkauan", Toast.LENGTH_SHORT).show()
               }
            }

        }

        setupView()


    }

    private fun setupView() {
        supportActionBar?.hide()
    }

    private fun isDistance(latitudeUser: Double, longitudeUser: Double) : Double{

        //variable
        val pi = 3.14159265358979
        val lat1 = -6.3538282
        val lon1 = 106.8412196
        val lat2 = latitudeUser
        val lon2 = longitudeUser
        val R = 6371e3

        val latRad1 = lat1 * (pi / 180)
        val latRad2 = lat2 * (pi / 180)
        val deltaLatRad = (lat2 - lat1) * (pi / 180)
        val deltaLonRad = (lon2 - lon1) * (pi / 180)

        /* RUMUS HAVERSINE */
        val a = sin(deltaLatRad / 2).pow(2) + cos(latRad1) * cos(latRad2) * sin(deltaLonRad / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val s = R * c // hasil jarak dalam meter
        return s
    }

}