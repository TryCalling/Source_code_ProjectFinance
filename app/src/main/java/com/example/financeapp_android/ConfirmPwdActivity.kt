package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.financeapp_android.databinding.ActivityConfirmPwdBinding

class ConfirmPwdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmPwdBinding
    private lateinit var originalPassword: String
    private val confirmPasswordBuilder = StringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmPwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        originalPassword = intent.getStringExtra("password") ?: ""

        // Load GIF into the ImageView using Glide
//        val gifUrl = "https://example.com/yourgif.gif" // Replace with your GIF URL or local resource
//        Glide.with(this)
//            .asGif()
//            .load(gifUrl)
//            .into(gifImageView)

        val gifImageView: ImageView = findViewById(R.id.gifImageView)
        Glide.with(this)
            .asGif()
            .load(R.drawable.arrow2)
            .into(gifImageView)

        setUpNumberPad()
    }

    private fun setUpNumberPad() {
        binding.btn0.setOnClickListener { onNumberClick(it) }
        binding.btn1.setOnClickListener { onNumberClick(it) }
        binding.btn2.setOnClickListener { onNumberClick(it) }
        binding.btn3.setOnClickListener { onNumberClick(it) }
        binding.btn4.setOnClickListener { onNumberClick(it) }
        binding.btn5.setOnClickListener { onNumberClick(it) }
        binding.btn6.setOnClickListener { onNumberClick(it) }
        binding.btn7.setOnClickListener { onNumberClick(it) }
        binding.btn8.setOnClickListener { onNumberClick(it) }
        binding.btn9.setOnClickListener { onNumberClick(it) }
        binding.btnDelete.setOnClickListener { onDeleteClick(it) }
    }

    private fun onNumberClick(view: View) {
        val button = view as Button
        val number = button.text.toString()
        if (confirmPasswordBuilder.length < 4) {
            confirmPasswordBuilder.append(number)
            updatePasswordDots()
            if (confirmPasswordBuilder.length == 4) {
                checkPasswords()
            }
        }
    }

    private fun onDeleteClick(view: View) {
        if (confirmPasswordBuilder.isNotEmpty()) {
            confirmPasswordBuilder.deleteCharAt(confirmPasswordBuilder.length - 1)
            updatePasswordDots()
        }
    }

    private fun updatePasswordDots() {
        // Clear existing dots
        binding.dot1.setBackgroundResource(R.drawable.password_dot_empty)
        binding.dot2.setBackgroundResource(R.drawable.password_dot_empty)
        binding.dot3.setBackgroundResource(R.drawable.password_dot_empty)
        binding.dot4.setBackgroundResource(R.drawable.password_dot_empty)

        // Set filled dots based on password length
        when (confirmPasswordBuilder.length) {
            1 -> binding.dot1.setBackgroundResource(R.drawable.password_dot_filled)
            2 -> {
                binding.dot1.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot2.setBackgroundResource(R.drawable.password_dot_filled)
            }
            3 -> {
                binding.dot1.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot2.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot3.setBackgroundResource(R.drawable.password_dot_filled)
            }
            4 -> {
                binding.dot1.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot2.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot3.setBackgroundResource(R.drawable.password_dot_filled)
                binding.dot4.setBackgroundResource(R.drawable.password_dot_filled)
            }
        }
    }

    private fun checkPasswords() {
        if (confirmPasswordBuilder.toString() == originalPassword) {
            Toast.makeText(this, "Password confirmed successfully!", Toast.LENGTH_SHORT).show()
            // Proceed to the next step, e.g., navigating to another activity
            // val intent = Intent(this, NextActivity::class.java)
            // startActivity(intent)
        } else {
            Toast.makeText(this, "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show()
            confirmPasswordBuilder.clear()
            updatePasswordDots()
        }
    }

}
