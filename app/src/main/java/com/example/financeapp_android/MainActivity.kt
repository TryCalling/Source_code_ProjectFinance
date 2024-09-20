package com.example.financeapp_android

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.financeapp_android.databinding.ActivityMainBinding
import com.example.financeapp_android.fragment.HistoryFragment
import com.example.financeapp_android.fragment.HomeFragment
import com.example.financeapp_android.fragment.ProfileFragment
import com.example.financeapp_android.fragment.StatisticFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Setup Bottom Navigation View
        binding.btnnavView.setOnNavigationItemSelectedListener { menuItem ->
            Log.d("MainActivity", "MenuItem selected: ${menuItem.title}")
            val selectedFragment = when (menuItem.itemId) {
                R.id.iconHome -> HomeFragment()
                R.id.iconHistory -> HistoryFragment()
                R.id.iconStatistic -> StatisticFragment()
                R.id.iconProfile -> ProfileFragment()
                else -> null
            }

            selectedFragment?.let {
                replaceFragment(it)
                animateTab(menuItem)
                true
            } ?: false
        }

        // Change the status bar color
        setStatusBarColor(R.color.color_splashscreen)

        // Load the default fragment
        replaceFragment(HomeFragment())
    }

    // Replace Fragment in the container
    private fun replaceFragment(fragment: Fragment) {
        Log.d("MainActivity", "Replacing fragment with ${fragment::class.java.simpleName}")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    // Animate Tab Selection
    private fun animateTab(menuItem: MenuItem) {
        val tabView = binding.btnnavView.findViewById<View>(menuItem.itemId)
        val animator = ObjectAnimator.ofFloat(tabView, "scaleX", 0.5f, 1f)
        animator.duration = 250
        animator.interpolator = android.view.animation.AccelerateDecelerateInterpolator()
        animator.start()
    }

    private fun setStatusBarColor(colorResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, colorResId)
        }
    }
}
