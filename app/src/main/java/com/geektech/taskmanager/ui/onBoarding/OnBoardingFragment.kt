package com.geektech.taskmanager.ui.onBoarding

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.geektech.taskmanager.databinding.FragmentOnBoardingBinding
import com.geektech.taskmanager.databinding.ItemViewPagerBinding
import com.geektech.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onBoardAdapter = OnBoardingAdapter()
        binding.viewPager.adapter = onBoardAdapter

        binding.indicator.setViewPager(binding.viewPager)
    }
}