package com.example.presensilabti.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presensilabti.R
import com.example.presensilabti.databinding.ActivityRegisterBinding
import com.example.presensilabti.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        transition()
    }

    private fun transition() {
        binding.btnLoginInRegister.setOnClickListener {
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
        }
    }
}