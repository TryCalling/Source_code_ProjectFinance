package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up button click listeners
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, CreatePwdActivity::class.java)
            startActivity(intent)
        }

        binding.phoneNumberButton.setOnClickListener {
            val intent = Intent(this, PhoneNumberActivity::class.java)
            startActivity(intent)
        }
    }
}
