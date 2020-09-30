package com.example.nigeriancuisine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nigeriancuisine.databinding.ListItemBinding


class FoodAdapter: androidx.recyclerview.widget.ListAdapter<Food, FoodAdapter.ViewHolder>(
    CoursesDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fd = getItem(position)
        // or (holder as ViewHolder).bind(foodEntities)
        holder.bind(fd)
    }

    class ViewHolder private constructor(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {
                binding.food?.let { no ->
                    navigateUni(no, it)
                }
            }
        }

        private fun navigateUni(food: Food?, it: View) {
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(food)
                it.findNavController().navigate(direction)
        }
        fun bind(item: Food) {
            binding.apply {
                food = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    class CoursesDiffUtilCallback: DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem

        }

    }
}