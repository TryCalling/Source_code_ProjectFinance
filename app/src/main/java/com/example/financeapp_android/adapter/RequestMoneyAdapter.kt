package com.example.financeapp_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.R
import com.example.financeapp_android.model.RequestMoneyItem

class RequestMoneyAdapter(private val items: List<RequestMoneyItem>, private val onAcceptClick: (RequestMoneyItem) -> Unit, private val onDeclineClick: (RequestMoneyItem) -> Unit) :
    RecyclerView.Adapter<RequestMoneyAdapter.MoneyRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_request_money, parent, false)
        return MoneyRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyRequestViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onAcceptClick, onDeclineClick)
    }

    override fun getItemCount(): Int = items.size

    class MoneyRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val transactionDate: TextView = itemView.findViewById(R.id.transactionDate)
        private val transactionAmount: TextView = itemView.findViewById(R.id.transactionAmount)
        private val acceptButton: Button = itemView.findViewById(R.id.acceptButton)
        private val declineButton: Button = itemView.findViewById(R.id.declineButton)

        fun bind(item: RequestMoneyItem, onAcceptClick: (RequestMoneyItem) -> Unit, onDeclineClick: (RequestMoneyItem) -> Unit) {
            profileImage.setImageResource(item.profileImageRes)
            userName.text = item.userName
            transactionDate.text = item.transactionDate
            transactionAmount.text = item.transactionAmount

            acceptButton.setOnClickListener { onAcceptClick(item) }
            declineButton.setOnClickListener { onDeclineClick(item) }
        }
    }
}
