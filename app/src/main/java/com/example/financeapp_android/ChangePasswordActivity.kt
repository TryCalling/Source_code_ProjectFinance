package com.example.financeapp_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            // Handle save password logic
        }

        binding.cancelButton.setOnClickListener {
            // Handle cancel logic
        }
    }
}
