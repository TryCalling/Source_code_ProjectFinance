package com.example.financeapp_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp_android.databinding.ItemsCardBinding
import com.example.financeapp_android.model.Card

class CardPagerAdapter(private val cardList: List<Card>) : RecyclerView.Adapter<CardPagerAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int = cardList.size

    class CardViewHolder(private val binding: ItemsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.tvCardLabel.text = card.label
            binding.tvCardNumber.text = card.number
            binding.tvCardBalance.text = card.balance
            binding.tvCardExpiry.text = card.expiry
        }
    }
}
