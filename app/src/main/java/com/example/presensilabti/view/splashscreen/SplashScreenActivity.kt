package com.example.presensilabti.view.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presensilabti.databinding.ActivitySplashScreenBinding
import com.example.presensilabti.view.login.LoginActivity
import com.example.presensilabti.view.main.MainUserActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        transition()

    }

    private fun transition() {
        binding.btnSplashScreen.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}