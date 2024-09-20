package com.example.financeapp_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.financeapp_android.fragment.DoneFragment
import com.example.financeapp_android.fragment.OnProgressFragment

class SavingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2 // Number of tabs/fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnProgressFragment()
            1 -> DoneFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
