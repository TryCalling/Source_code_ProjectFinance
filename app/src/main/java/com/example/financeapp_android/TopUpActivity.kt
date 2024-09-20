package com.example.financeapp_android

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivityTopUpBinding

class TopUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            onBackPressed()
        }
    }


    private fun setupListeners() {
        binding.btnProceed.setOnClickListener {
            Toast.makeText(this, "Proceeding with top-up...", Toast.LENGTH_SHORT).show()
            showTopUpSuccessDialog()
            // Handle the proceed action here
        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, "Top-up canceled", Toast.LENGTH_SHORT).show()
            // Handle the cancel action here
        }

        // Button click listeners for specific amounts
        binding.btn50.setOnClickListener { updateAmount(50) }
        binding.btn100.setOnClickListener { updateAmount(100) }
        binding.btn150.setOnClickListener { updateAmount(150) }
        binding.btn200.setOnClickListener { updateAmount(200) }
        binding.btn250.setOnClickListener { updateAmount(250) }
        binding.btn300.setOnClickListener { updateAmount(300) }
        binding.btn350.setOnClickListener { updateAmount(350) }
        binding.btn400.setOnClickListener { updateAmount(400) }
        binding.btn450.setOnClickListener { updateAmount(450) }

        // Plus and Minus buttons
        binding.btnPlus.setOnClickListener { incrementAmount() }
        binding.btnMinus.setOnClickListener { decrementAmount() }

        // SeekBar listener
        binding.amountSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAmount(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: Handle any action when the user starts to drag the thumb
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional: Handle any action when the user stops dragging the thumb
            }
        })
    }

    private fun incrementAmount() {
        val currentAmount = binding.amountSlider.progress
        if (currentAmount < binding.amountSlider.max) {
            updateAmount(currentAmount + 1)
        }
    }

    private fun decrementAmount() {
        val currentAmount = binding.amountSlider.progress
        if (currentAmount > 0) {
            updateAmount(currentAmount - 1)
        }
    }

    private fun updateAmount(amount: Int) {
        binding.tvAmount.text = "$$amount"
        binding.amountSlider.progress = amount
        Toast.makeText(this, "Amount set to $$amount", Toast.LENGTH_SHORT).show()
    }

    // Method to show success dialog
    private fun showTopUpSuccessDialog() {
        // Inflate the custom layout for the dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_top_up_success, null)

        // Create the Dialog instance
        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(dialogView)

        // Set dialog window to full-screen
        val window = dialog.window
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }

        // Initialize the views in the dialog
        val successIcon = dialogView.findViewById<ImageView>(R.id.ivSuccessIcon)
        val successMessage = dialogView.findViewById<TextView>(R.id.tvSuccessMessage)
        val descriptionText = dialogView.findViewById<TextView>(R.id.tvDescription)
        val btnContinue = dialogView.findViewById<Button>(R.id.btnContinue)

        // Set up any additional customization
        successIcon.setImageResource(R.drawable.img_5)  // Ensure this drawable exists
        successMessage.text = "Top Up Successfully"
        descriptionText.text = "The amount will be reflected in your account within a few minutes"

        // Set click listener for the "Continue" button
        btnContinue.setOnClickListener {
            dialog.dismiss()
            // Handle what happens after the dialog is dismissed, e.g., navigate to another screen
        }

        // Show the dialog
        dialog.show()
    }

}
