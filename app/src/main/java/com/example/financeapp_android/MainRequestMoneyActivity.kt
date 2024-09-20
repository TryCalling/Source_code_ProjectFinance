package com.example.financeapp_android

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.adapter.RequestMoneyAdapter
import com.example.financeapp_android.model.RequestMoneyItem

class MainRequestMoneyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainrequest_money)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleOfRequestMoney)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            RequestMoneyItem(R.drawable.img_25, "Yulisa Meyun", "Tuesday, 23 Nov 2021", "$487.00"),
            RequestMoneyItem(R.drawable.img_25, "Fanny Alison", "Friday, 19 Oct 2021", "$178.00"),
            RequestMoneyItem(R.drawable.img_25, "Vina Andini", "Sunday, 09 May 2021", "$364.00"),
            RequestMoneyItem(R.drawable.img_25, "Andi Taher", "Tuesday, 14 Mar 2021", "$543.00"),
            RequestMoneyItem(R.drawable.img_25, "John Arbalika", "Saturday, 04 Feb 2021", "$234.00")
        )

        val adapter = RequestMoneyAdapter(items, onAcceptClick = { item ->
            // Handle accept click
        }, onDeclineClick = { item ->
            // Handle decline click
        })

        recyclerView.adapter = adapter

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            onBackPressed()
        }
    }


}
