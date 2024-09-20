package com.example.financeapp_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.financeapp_android.fragment.AllNotificationsFragment
import com.example.financeapp_android.fragment.UnreadNotificationsFragment

class NotificationPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllNotificationsFragment()
            1 -> UnreadNotificationsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
