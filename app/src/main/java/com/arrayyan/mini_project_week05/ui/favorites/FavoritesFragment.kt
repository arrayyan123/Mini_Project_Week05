package com.arrayyan.mini_project_week05.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arrayyan.mini_project_week05.data.network.MealApi
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import com.arrayyan.mini_project_week05.databinding.FragmentFavoritesBinding
import com.bumptech.glide.Glide

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory(MealRepository(MealApi.create()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel.randomMeal.observe(viewLifecycleOwner) { meal ->
            if (meal != null) {
                binding.sampleMealName.text = meal.strMeal
                Glide.with(this)
                    .load(meal.strMealThumb)
                    .placeholder(android.R.color.darker_gray)
                    .into(binding.sampleImage)
            } else {
                binding.sampleMealName.text = "Failed to load random meal"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
