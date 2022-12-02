package com.geektech.taskmanager.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.R
import com.geektech.taskmanager.data.local.Pref
import com.geektech.taskmanager.databinding.FragmentOnBoardingBinding
import com.geektech.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter
import com.google.firebase.auth.FirebaseAuth


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref
    private var auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        val onBoardAdapter = OnBoardingAdapter {
            if (auth.currentUser?.uid  == null) {
                findNavController().navigate(R.id.authFragment)
            } else {
                pref.saveShowBoarding(true)
                findNavController().navigateUp()
            }
        }

        binding.viewPager.adapter = onBoardAdapter

        binding.indicator.setViewPager(binding.viewPager)

    }

}