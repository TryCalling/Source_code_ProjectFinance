package com.example.financeapp_android

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.financeapp_android.databinding.ActivityMainonboardingBinding
import com.example.financeapp_android.fragment.OnboardingFragment

class MainOnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainonboardingBinding
    private lateinit var dots: Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainonboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager2 with the adapter
        val fragments = listOf(
            OnboardingFragment.newInstance(R.drawable.img_1, "Send Money", "Send money easily and with one click everything will be sent every time you make a transaction", false),
            OnboardingFragment.newInstance(R.drawable.img_2, "Request Money", "You can request money to friends or family to send as much money as you want", false),
            OnboardingFragment.newInstance(R.drawable.img_3, "Easy To Use", "Very easy to use and easy to understand for those of you who are beginners", true)
        )

        val onboardingAdapter = OnboardingAdapter(this, fragments)
        binding.viewPager.adapter = onboardingAdapter
        setupDots()
        setCurrentDot(0)

        // Set up the ViewPager2 page change callback to update the dots
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentDot(position)
            }
        })
    }

    private fun setupDots() {
        dots = arrayOfNulls(3) // Number of onboarding items
        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = getString(R.string.dot)
                textSize = 35f
                setTextColor(ContextCompat.getColor(this@MainOnboardingActivity, R.color.inactive_dot))
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.dotsLayout.addView(dots[i], params)
        }
    }

    private fun setCurrentDot(position: Int) {
        for (i in dots.indices) {
            dots[i]?.setTextColor(ContextCompat.getColor(this, R.color.inactive_dot))
        }
        dots[position]?.setTextColor(ContextCompat.getColor(this, R.color.active_dot))
    }

    fun moveToNextPage() {
        val currentItem = binding.viewPager.currentItem
        if (currentItem + 1 < binding.viewPager.adapter?.itemCount ?: 0) {
            binding.viewPager.currentItem = currentItem + 1
        }
    }
}
