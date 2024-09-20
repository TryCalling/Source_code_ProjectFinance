package com.example.financeapp_android.model

data class NotificationItem(
    val icon: Int,
    val title: String,
    val time: String,
    var isRead: Boolean = false // New property to track the read/unread state

)
