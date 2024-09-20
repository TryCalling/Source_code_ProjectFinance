package com.example.financeapp_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivityAccountCreatedBinding

class AccountCreatedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountCreatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountCreatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle continue button click
        binding.continueButton.setOnClickListener {
            // Handle continue button logic
        }

        // Handle terms and conditions link click
        binding.termsLink.setOnClickListener {
            // Handle terms and conditions link logic
        }
    }
}
