package com.example.presensilabti.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presensilabti.R
import com.example.presensilabti.databinding.ActivityLoginBinding
import com.example.presensilabti.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        transition()
    }

    private fun transition() {
        binding.btnRegisterInLogin.setOnClickListener{
            val intentToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }

    }
}