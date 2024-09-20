package com.example.financeapp_android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.R
import com.example.financeapp_android.adapter.SavingListAdapter
import com.example.financeapp_android.model.SavingItem

class DoneFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SavingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)

        recyclerView = view.findViewById(R.id.savingRecyclerView) // Corrected ID for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val items = listOf(
            SavingItem(R.drawable.img_24, "Buy Mini Vespa", "Mercedez Benz 001", 100),
            SavingItem(R.drawable.img_24, "Buy Barbie Doll", "One Set Purple", 100)
        )

        adapter = SavingListAdapter(items)
        recyclerView.adapter = adapter

        return view
    }
}
