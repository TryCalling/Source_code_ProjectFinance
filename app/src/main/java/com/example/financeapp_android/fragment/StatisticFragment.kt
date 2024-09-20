package com.example.financeapp_android.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.financeapp_android.R
import com.example.financeapp_android.adapter.CardPagerAdapter
import com.example.financeapp_android.databinding.FragmentStatisticBinding
import com.example.financeapp_android.model.Card
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import android.widget.ArrayAdapter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)

        setupViewPager()
        setupChart(binding.lineChart)
        setupSpinner(binding.timeFilterSpinner)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewPager() {
        val cardList = listOf(
            Card("Payment Card", "2298 1268 3398 9874", "$2885.00", "12/24"),
            Card("Payment Card", "2298 1268 3398 9875", "$3200.00", "11/23")
        )

        val adapter = CardPagerAdapter(cardList)
        binding.viewPager.adapter = adapter

        // Set up dots in LinearLayout
        setupDots(cardList.size)

        // Attach ViewPager change listener to update dots
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
            }
        })
    }

    private fun setupDots(count: Int) {
        val dotsLayout = binding.dotsLayout
        val dots = arrayOfNulls<ImageView>(count)

        for (i in dots.indices) {
            dots[i] = ImageView(requireContext()).apply {
                setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dot_inactive))
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(8, 0, 8, 0)
                dotsLayout.addView(this, params)
            }
        }

        // Set the first dot as active
        if (dots.isNotEmpty()) {
            dots[0]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dot_active))
        }
    }

    private fun updateDots(position: Int) {
        val dotsLayout = binding.dotsLayout
        val childCount = dotsLayout.childCount

        for (i in 0 until childCount) {
            val imageView = dotsLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dot_active))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dot_inactive))
            }
        }
    }

    private fun setupChart(chart: LineChart) {
        // Data entries for Income
        val entriesIncome = listOf(
            Entry(0f, 20f), Entry(1f, 22f), Entry(2f, 18f),
            Entry(3f, 24f), Entry(4f, 20f), Entry(5f, 26f),
            Entry(3f, 24f)
        )

        // Data entries for Expenses
        val entriesExpenses = listOf(
            Entry(0f, 15f), Entry(1f, 16f), Entry(2f, 14f),
            Entry(3f, 18f), Entry(4f, 16f), Entry(5f, 20f)
        )

        // Set up the Income dataset
        val incomeDataSet = LineDataSet(entriesIncome, "Income").apply {
            color = Color.parseColor("#F2994A") // Orange color
            lineWidth = 2f
            circleRadius = 4f
            setCircleColor(color)
            setDrawValues(false) // Hide point values
            setDrawCircles(true) // Show circles
            mode = LineDataSet.Mode.CUBIC_BEZIER // Smooth the line
        }

        // Set up the Expenses dataset
        val expensesDataSet = LineDataSet(entriesExpenses, "Expenses").apply {
            color = Color.parseColor("#2F80ED") // Blue color
            lineWidth = 2f
            circleRadius = 4f
            setCircleColor(color)
            setDrawValues(false) // Hide point values
            setDrawCircles(true) // Show circles
            mode = LineDataSet.Mode.CUBIC_BEZIER // Smooth the line
        }

        // Combine datasets
        val lineData = LineData(incomeDataSet, expensesDataSet)
        chart.data = lineData

        // Customize X-Axis
        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.textColor = Color.GRAY
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))

        // Customize Left Y-Axis
        val leftAxis: YAxis = chart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.textColor = Color.GRAY

        // Disable Right Y-Axis
        val rightAxis: YAxis = chart.axisRight
        rightAxis.isEnabled = false

        // Disable chart description
        chart.description.isEnabled = false

        // Disable the Legend
        val legend = chart.legend
        legend.isEnabled = false

        // Refresh the chart with the new data
        chart.invalidate()
    }

    private fun setupSpinner(spinner: Spinner) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.time_filter_options,
            R.layout.spinner_item // Custom layout for spinner items
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        // Set the item selected listener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Handle the selected item here
                // For example, you can display it in a Toast or update some UI elements
                // Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where nothing is selected, if necessary
            }
        }
    }
}
