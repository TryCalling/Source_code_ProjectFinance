package com.example.financeapp_android.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.financeapp_android.MainOnboardingActivity
import com.example.financeapp_android.SignInActivity
import com.example.financeapp_android.SignUpActivity
import com.example.financeapp_android.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_IMAGE_RES = "imageRes"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IS_LAST_PAGE = "isLastPage"

        fun newInstance(imageRes: Int, title: String, description: String, isLastPage: Boolean): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES, imageRes)
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putBoolean(ARG_IS_LAST_PAGE, isLastPage)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let {
            binding.imageView.setImageResource(it.getInt(ARG_IMAGE_RES))
            binding.titleView.text = it.getString(ARG_TITLE)
            binding.descriptionView.text = it.getString(ARG_DESCRIPTION)
            val isLastPage = it.getBoolean(ARG_IS_LAST_PAGE)
            binding.nextButton.visibility = if (isLastPage) View.GONE else View.VISIBLE
            binding.skipButton.visibility = if (isLastPage) View.GONE else View.VISIBLE
            binding.createAccountButton.visibility = if (isLastPage) View.VISIBLE else View.GONE
            binding.loginNowButton.visibility = if (isLastPage) View.VISIBLE else View.GONE
        }

        binding.createAccountButton.setOnClickListener {
            val intent = Intent(activity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginNowButton.setOnClickListener {
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.nextButton.setOnClickListener {
            (activity as? MainOnboardingActivity)?.moveToNextPage()
        }

        binding.skipButton.setOnClickListener {
            (activity as? MainOnboardingActivity)?.moveToNextPage()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
