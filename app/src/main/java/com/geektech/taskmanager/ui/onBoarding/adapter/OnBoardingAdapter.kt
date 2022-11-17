package com.geektech.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.geektech.taskmanager.databinding.ItemViewPagerBinding
import com.geektech.taskmanager.model.OnBoard

class OnBoardingAdapter:RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private var onBoardingList = arrayListOf<OnBoard>(
        OnBoard("", "t1", "1"),
        OnBoard("", "t2", "2"),
        OnBoard("", "t3", "3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingList[position])
    }

    override fun getItemCount(): Int = onBoardingList.size
    inner class OnBoardingViewHolder(private val binding:ItemViewPagerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(onBoard:OnBoard){
            binding.btnStart.isVisible = adapterPosition == onBoardingList.lastIndex
            binding.btnSkip.isVisible = adapterPosition != onBoardingList.lastIndex

            binding.tvTitle.text = onBoard.title
            binding.tvDescription.text = onBoard.description

            binding.btnSkip.setOnClickListener{

            }
        }
    }
}