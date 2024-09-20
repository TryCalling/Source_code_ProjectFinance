package com.example.financeapp_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.R
import com.example.financeapp_android.model.SavingItem

class SavingListAdapter(private val items: List<SavingItem>) : RecyclerView.Adapter<SavingListAdapter.SavingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_transaction_saving, parent, false)
        return SavingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class SavingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SavingItem) {
            itemView.findViewById<ImageView>(R.id.itemIcon).setImageResource(item.icon)
            itemView.findViewById<TextView>(R.id.itemTitle).text = item.title
            itemView.findViewById<TextView>(R.id.itemSubtitle).text = item.subtitle
            itemView.findViewById<TextView>(R.id.itemPercentage).text = "${item.percentage}%"
        }
    }
}
