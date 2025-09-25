package com.arrayyan.mini_project_week05.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.arrayyan.mini_project_week05.R
import com.arrayyan.mini_project_week05.data.model.MealSummary
import com.bumptech.glide.Glide

class MealDropdownAdapter(
    context: Context,
    private val meals: List<MealSummary>
) : ArrayAdapter<MealSummary>(context, 0, meals) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: View.inflate(context, R.layout.item_dropdown_meal, null)
        val meal = getItem(position)

        val title = view.findViewById<TextView>(R.id.title)
        val thumb = view.findViewById<ImageView>(R.id.thumb)

        title.text = meal?.strMeal
        Glide.with(context)
            .load(meal?.strMealThumb)
            .placeholder(android.R.color.darker_gray)
            .into(thumb)

        return view
    }
}
