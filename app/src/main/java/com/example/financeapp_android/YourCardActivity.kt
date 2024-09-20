package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.financeapp_android.databinding.ActivityYourCardBinding

class YourCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityYourCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle back arrow press
        binding.backArrow.setOnClickListener {
            onBackPressed() // Go back to the previous activity
        }

        // Handle the options menu press
        binding.optionsMenu.setOnClickListener {
            // Show options or a menu here
            Toast.makeText(this, "Options menu clicked", Toast.LENGTH_SHORT).show()
        }

        // Handle the Add Credit Card button press
        binding.btnAddCreditCard.setOnClickListener {
            // Handle the click, e.g., open a new activity or show a dialog
            Toast.makeText(this, "Add Credit Card button clicked", Toast.LENGTH_SHORT).show()
        }

        // Handle the Add Credit Card button press
        binding.btnAddCreditCard.setOnClickListener {
            // Navigate to the AddCardActivity
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }
}
