package com.example.financeapp_android

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class SendMoneySuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money_success)

        val barcodeImageView: ImageView = findViewById(R.id.barcodeImageView)

        // Replace this with your transaction data
        val transactionData = "123456789012"

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(transactionData, BarcodeFormat.CODE_128, 600, 150)
            barcodeImageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Set up the Back to Home button
        val backToHomeButton: Button = findViewById(R.id.backToHomeButton)
        backToHomeButton.setOnClickListener {
            finish() // Close the activity and go back to home
        }
    }
}
