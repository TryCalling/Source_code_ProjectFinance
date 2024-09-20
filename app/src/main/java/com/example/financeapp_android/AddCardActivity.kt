package com.example.financeapp_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.financeapp_android.databinding.ActivityAddCardBinding
import com.google.android.material.textfield.TextInputEditText

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle back arrow press
        binding.backArrow.setOnClickListener {
            onBackPressed() // Go back to the previous activity
        }

        // Handle Save Card button press
        binding.btnSaveCard.setOnClickListener {
            // Handle saving the card details, e.g., saving to database or making an API call
            Toast.makeText(this, "Card Saved", Toast.LENGTH_SHORT).show()
            // Optionally, finish the activity to go back to the previous screen
            finish()
        }

        // Handle the options menu press
        binding.optionsMenu.setOnClickListener {
            // Show options or a menu here
            Toast.makeText(this, "Options menu clicked", Toast.LENGTH_SHORT).show()
        }

    }
}
