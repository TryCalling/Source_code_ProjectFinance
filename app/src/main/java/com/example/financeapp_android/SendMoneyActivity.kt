package com.example.financeapp_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import java.text.NumberFormat

class SendMoneyActivity : AppCompatActivity() {

    private lateinit var amountTextView: TextView
    private lateinit var keypad: View
    private lateinit var descriptionEditText: TextInputEditText
    private var currentAmount: Long = 0L
    private val MAX_AMOUNT = 99999999L // $999,999.99 in cents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)

        // Initialize views
        amountTextView = findViewById(R.id.tv_amount)
        keypad = findViewById(R.id.keyNumber)
        descriptionEditText = findViewById(R.id.descriptionEditText)

        // Set up the click listeners for the number buttons
        findViewById<AppCompatButton>(R.id.btn1).setOnClickListener { onNumberClick("1") }
        findViewById<AppCompatButton>(R.id.btn2).setOnClickListener { onNumberClick("2") }
        findViewById<AppCompatButton>(R.id.btn3).setOnClickListener { onNumberClick("3") }
        findViewById<AppCompatButton>(R.id.btn4).setOnClickListener { onNumberClick("4") }
        findViewById<AppCompatButton>(R.id.btn5).setOnClickListener { onNumberClick("5") }
        findViewById<AppCompatButton>(R.id.btn6).setOnClickListener { onNumberClick("6") }
        findViewById<AppCompatButton>(R.id.btn7).setOnClickListener { onNumberClick("7") }
        findViewById<AppCompatButton>(R.id.btn8).setOnClickListener { onNumberClick("8") }
        findViewById<AppCompatButton>(R.id.btn9).setOnClickListener { onNumberClick("9") }
        findViewById<AppCompatButton>(R.id.btn0).setOnClickListener { onNumberClick("0") }

        // Set up the click listener for the delete button
        findViewById<ImageButton>(R.id.btnDelete).setOnClickListener { onDeleteClick() }

        // Set up the click listeners for the plus and minus buttons
        findViewById<ImageView>(R.id.btn_plus).setOnClickListener { incrementAmount() }
        findViewById<ImageView>(R.id.btn_minus).setOnClickListener { decrementAmount() }

        // Show the keypad when the amount text is clicked
        amountTextView.setOnClickListener {
            hideSoftKeyboard(descriptionEditText)
            showKeypad()
        }

        // Hide the keypad when the description field is clicked and show the standard keyboard
        descriptionEditText.setOnClickListener {
            hideKeypad()
            showSoftKeyboard(descriptionEditText)
        }

        // Set up the back button to finish the activity
        findViewById<ImageView>(R.id.backArrow).setOnClickListener {
            finish()
        }

        // Initialize the amount display
        updateAmount(currentAmount) // Start with $0.00

        // Navigate to success screen on clicking send money
        findViewById<Button>(R.id.sendMoneyButton).setOnClickListener {
            val intent = Intent(this, SendMoneySuccessActivity::class.java)
            startActivity(intent)
            finish() // Optional: close the current activity
        }
    }


    private fun onNumberClick(number: String) {
        val potentialAmount = (currentAmount * 10) + number.toLong()

        // Check if the new amount exceeds the limit
        if (potentialAmount <= MAX_AMOUNT) {
            currentAmount = potentialAmount
            updateAmount(currentAmount)
        } else {
            // Optionally, show a message or feedback indicating the maximum limit has been reached
        }
    }

    private fun onDeleteClick() {
        // Remove the last digit from the current amount
        currentAmount = currentAmount / 10
        updateAmount(currentAmount)
    }

    private fun incrementAmount() {
        if (currentAmount + 100 <= MAX_AMOUNT) {
            currentAmount += 100 // Increase by $1
            updateAmount(currentAmount)
        } else {
            // Optionally, show a message or feedback indicating the maximum limit has been reached
        }
    }

    private fun decrementAmount() {
        if (currentAmount >= 100) {
            currentAmount -= 100 // Decrease by $1
            updateAmount(currentAmount)
        }
    }

    private fun updateAmount(newAmount: Long) {
        currentAmount = newAmount
        amountTextView.text = formatAmount(currentAmount)
    }

    private fun formatAmount(amount: Long): String {
        val formatter: NumberFormat = DecimalFormat("##,##0.00")
        val dollars = amount / 100
        val cents = amount % 100
        return "$${formatter.format(dollars + cents / 100.0)}"
    }

    private fun showKeypad() {
        keypad.visibility = View.VISIBLE
    }

    private fun hideKeypad() {
        keypad.visibility = View.GONE
    }

    private fun showSoftKeyboard(view: View) {
        view.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideSoftKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
