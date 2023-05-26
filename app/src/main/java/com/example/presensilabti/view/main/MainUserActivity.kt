package com.example.presensilabti.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presensilabti.R
import com.example.presensilabti.databinding.ActivityMainBinding
import com.example.presensilabti.view.absen.AbsenUserActivity

class MainUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupView()
    }

    private fun setupView() {
        supportActionBar?.hide()

        binding.buttonAbsen.setOnClickListener {
            val intent = Intent(this, AbsenUserActivity::class.java)
            startActivity(intent)
        }

    }
}