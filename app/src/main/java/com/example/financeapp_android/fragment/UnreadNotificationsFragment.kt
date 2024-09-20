package com.example.financeapp_android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.R
import com.example.financeapp_android.adapter.NotificationAdapter
import com.example.financeapp_android.model.NotificationItem

class UnreadNotificationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerView = view.findViewById(R.id.notificationRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val items = listOf(
            NotificationItem(R.drawable.img_24, "You have received money from Dean Williamson +$76.00", "10:42 AM"),
            NotificationItem(R.drawable.img_26, "You have sent money to Alexandarius Williamson -$23.00", "10:10 AM"),
            // Add more notifications here...
        )

        adapter = NotificationAdapter(items)
        recyclerView.adapter = adapter

        return view
    }
}
