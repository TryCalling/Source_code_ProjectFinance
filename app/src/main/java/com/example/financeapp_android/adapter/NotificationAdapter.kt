package com.example.financeapp_android.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.R
import com.example.financeapp_android.model.NotificationItem

class NotificationAdapter(private val items: List<NotificationItem>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_allnotification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val notificationDot: ImageView = itemView.findViewById(R.id.notificationDot)
        private val notificationTitle: TextView = itemView.findViewById(R.id.notificationTitle)
        private val notificationTime: TextView = itemView.findViewById(R.id.notificationTime)
        private val cardOfNotification: View = itemView.findViewById(R.id.cardOfNotification)


        fun bind(item: NotificationItem) {
            notificationTitle.text = item.title
            notificationTime.text = item.time

            //Set the initial state of the dot based on the read/unread status
            notificationDot.visibility = if (item.isRead) View.INVISIBLE else View.VISIBLE

            // Handle click on the card to change the read/unread status and hide the dot
            cardOfNotification.setOnClickListener {
                item.isRead = true
                notificationDot.visibility = View.INVISIBLE
            }
        }
    }
}
