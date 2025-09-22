package com.arrayyan.mini_project_week05.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.arrayyan.mini_project_week05.data.model.MealSummary
import com.arrayyan.mini_project_week05.databinding.ItemMealBinding

class MealAdapter(private val onClick: (MealSummary) -> Unit) :
    ListAdapter<MealSummary, MealAdapter.VH>(DIFF) {

    class VH(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.binding.title.text = item.strMeal
        Glide.with(holder.itemView)
            .load(item.strMealThumb)
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.darker_gray)
            .into(holder.binding.thumb)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<MealSummary>() {
            override fun areItemsTheSame(oldItem: MealSummary, newItem: MealSummary) =
                oldItem.idMeal == newItem.idMeal

            override fun areContentsTheSame(oldItem: MealSummary, newItem: MealSummary) =
                oldItem == newItem
        }
    }
}