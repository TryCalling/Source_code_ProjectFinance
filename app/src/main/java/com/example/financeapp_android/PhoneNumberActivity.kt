package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivityPhoneNumberBinding

class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle button clicks and any other logic here
        binding.sendCodeButton.setOnClickListener {
            val intent = Intent(this, VerifyAccountActivity::class.java)
            startActivity(intent)
            // Handle send code logic
        }

        binding.signUpWithEmailButton.setOnClickListener {
            // Handle sign up with email logic
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }


}
