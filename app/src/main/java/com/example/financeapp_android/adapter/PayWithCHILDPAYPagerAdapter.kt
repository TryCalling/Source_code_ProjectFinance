package com.example.financeapp_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.financeapp_android.fragment.MyQRFragment
import com.example.financeapp_android.fragment.ScanQRFragment

class PayWithCHILDPAYPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScanQRFragment()
            1 -> MyQRFragment()
            else -> ScanQRFragment()
        }
    }
}
