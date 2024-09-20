package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.financeapp_android.databinding.ActivityCreatePwdBinding

class CreatePwdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePwdBinding
    private val passwordBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        if (passwordBuilder.length < 4) {
            passwordBuilder.append(number)
            updatePasswordDots()
            if (passwordBuilder.length == 4) {
                navigateToConfirmPwd()
            }
        }
    }

    private fun onDeleteClick(view: View) {
        if (passwordBuilder.isNotEmpty()) {
            passwordBuilder.deleteCharAt(passwordBuilder.length - 1)
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
        when (passwordBuilder.length) {
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

    private fun navigateToConfirmPwd() {
        val intent = Intent(this, ConfirmPwdActivity::class.java)
        intent.putExtra("password", passwordBuilder.toString())
        startActivity(intent)
    }
}
