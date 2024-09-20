package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp_android.databinding.ActivityVerifyAccountBinding

class VerifyAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add logic to handle verification code input and actions

        // Automatically move focus to next EditText when a digit is entered
        binding.verifyCodeDigit1.addTextChangedListener(GenericTextWatcher(binding.verifyCodeDigit1, binding.verifyCodeDigit2))
        binding.verifyCodeDigit2.addTextChangedListener(GenericTextWatcher(binding.verifyCodeDigit2, binding.verifyCodeDigit3))
        binding.verifyCodeDigit3.addTextChangedListener(GenericTextWatcher(binding.verifyCodeDigit3, binding.verifyCodeDigit4))
        binding.verifyCodeDigit4.addTextChangedListener(GenericTextWatcher(binding.verifyCodeDigit4, null))

        // Handle resend code logic
        binding.resendCodeLink.setOnClickListener {
            // Handle resend code logic
        }

        // Handle verify now button click
        binding.verifyNowButton.setOnClickListener {
            showLoading()
            // Simulate verification process
            verifyCode()
        }
    }

    private fun showLoading() {
//        binding.blurOverlay.visibility = View.GONE
        binding.loadingIndicator.visibility = View.VISIBLE
        binding.verifyNowButton.visibility = View.GONE
    }

    private fun hideLoading() {
//        binding.blurOverlay.visibility = View.GONE
        binding.loadingIndicator.visibility = View.GONE
        binding.verifyNowButton.visibility = View.VISIBLE
    }

    private fun verifyCode() {
        // Simulate network call
        binding.root.postDelayed({
            hideLoading()
            navigateToAccountCreated()
        }, 3500) // Simulate delay
    }

    private fun navigateToAccountCreated() {
        val intent = Intent(this, AccountCreatedActivity::class.java)
        startActivity(intent)
        finish()
    }
}

class GenericTextWatcher(private val currentView: EditText, private val nextView: EditText?) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        if (currentView.text.length == 1) {
            nextView?.requestFocus()
        }
    }
    override fun afterTextChanged(editable: Editable) {}
}
