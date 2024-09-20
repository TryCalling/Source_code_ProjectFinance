package com.example.financeapp_android.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.financeapp_android.MainNotificationActivity
import com.example.financeapp_android.MainPayWithChildPayActivity
import com.example.financeapp_android.MainRequestMoneyActivity
import com.example.financeapp_android.MainYourSavingActivity
import com.example.financeapp_android.SendMoneyActivity
import com.example.financeapp_android.TopUpActivity
import com.example.financeapp_android.YourCardActivity
import com.example.financeapp_android.adapter.CardPagerAdapter
import com.example.financeapp_android.databinding.FragmentHomeBinding
import com.example.financeapp_android.model.Card

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // SwipeRefreshLayout
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            refreshContent()
        }

        // Set up ViewPager2 with CardPagerAdapter
        setupViewPager()

        // Set up button click listeners
        setupButtonListeners()

        return view
    }

    private fun setupViewPager() {
        // Sample list of cards
        val cards = listOf(
            Card("Visa Card", "2298 1268 3398 9874", "$2887.65", "12/24"),
            Card("MasterCard", "4598 4456 1234 5678", "$1024.32", "11/23"),
            Card("AMEX", "3739 123456 78901", "$1547.89", "10/25")
        )

        // Initialize the adapter
        val cardPagerAdapter = CardPagerAdapter(cards)

        // Set up ViewPager2
        binding.viewPager.apply {
            adapter = cardPagerAdapter
            binding.viewPager.adapter = cardPagerAdapter
        }

        // (Optional) You can set up page indicators here (dotsLayout) if needed
    }

    private fun setupButtonListeners() {
        binding.yourSaving.setOnClickListener {
            val intent = Intent(requireContext(), MainYourSavingActivity::class.java)
            startActivity(intent)
        }

        binding.viewAll.setOnClickListener {
            val intent = Intent(requireContext(), MainNotificationActivity::class.java)
            startActivity(intent)
        }

        binding.iconRequestMoney.setOnClickListener {
            val intent = Intent(requireContext(), MainRequestMoneyActivity::class.java)
            startActivity(intent)
        }

        binding.iconPay.setOnClickListener {
            val intent = Intent(requireContext(), MainPayWithChildPayActivity::class.java)
            startActivity(intent)
        }

        binding.iConTopUp.setOnClickListener {
            val intent = Intent(requireContext(), TopUpActivity::class.java)
            startActivity(intent)
        }

        binding.yourCard.setOnClickListener {
            val intent = Intent(requireContext(), YourCardActivity::class.java)
            startActivity(intent)
        }

        binding.iConSendMoney.setOnClickListener {
            val intent = Intent(requireContext(), SendMoneyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshContent() {
        Handler(Looper.getMainLooper()).postDelayed({
            swipeRefreshLayout.isRefreshing = false
            // Update UI here if needed after refresh
        }, 1500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
