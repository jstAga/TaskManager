package com.geektech.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.ItemViewPagerBinding
import com.geektech.taskmanager.data.model.OnBoard

class OnBoardingAdapter(val onItemClick:() -> Unit):RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private var onBoardingList = arrayListOf(
        OnBoard(R.raw.looti1, "Work on time", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        OnBoard(R.raw.lotii2, "Track progress", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor. "),
        OnBoard(R.raw.lotti3, "Remember daily task", "Tortor aliquam nulla facilisi cras fermentum odio eu. Risus in hendrerit gravida rutrum quisque non tellus.")
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
            onBoard.image?.let { binding.ivPicture.setAnimation(it) }
            binding.tvTitle.text = onBoard.title
            binding.tvDescription.text = onBoard.description

            binding.btnSkip.setOnClickListener{
                onItemClick()
            }

            binding.btnStart.setOnClickListener{
                onItemClick()
            }
        }
    }
}