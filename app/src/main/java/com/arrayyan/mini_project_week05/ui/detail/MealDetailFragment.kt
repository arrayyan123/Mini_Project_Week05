package com.arrayyan.mini_project_week05.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.arrayyan.mini_project_week05.databinding.FragmentMealDetailBinding
import com.arrayyan.mini_project_week05.viewmodel.MealDetailViewModel

class MealDetailFragment : Fragment() {

    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MealDetailFragmentArgs by navArgs()
    private val vm: MealDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mealId = args.mealId

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        vm.loadDetail(args.mealId)

        vm.meal.observe(viewLifecycleOwner) { detail ->
            detail?.let {
                binding.title.text = it.strMeal
                binding.category.text = it.strCategory ?: ""
                binding.area.text = it.strArea ?: ""
                Glide.with(view).load(it.strMealThumb)
                    .placeholder(android.R.color.darker_gray)
                    .error(android.R.color.darker_gray)
                    .into(binding.imageView)
                val sb = StringBuilder()
                it.ingredientsList().forEach { (ing, meas) ->
                    sb.append("• $ing — ${meas.ifBlank { "-" }}\n")
                }
                binding.ingredients.text = sb.toString()
                binding.instructions.text = it.strInstructions
            }
        }

        vm.error.observe(viewLifecycleOwner) { err ->
            err?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}