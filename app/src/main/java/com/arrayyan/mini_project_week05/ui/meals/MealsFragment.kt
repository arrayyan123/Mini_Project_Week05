package com.arrayyan.mini_project_week05.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arrayyan.mini_project_week05.databinding.FragmentMealsBinding
import com.arrayyan.mini_project_week05.viewmodel.MealsViewModel
import com.arrayyan.mini_project_week05.ui.adapter.MealAdapter

class MealsFragment : Fragment() {

    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!
    private val args: MealsFragmentArgs by navArgs()
    private val vm: MealsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.title = args.categoryName
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        val adapter = MealAdapter { meal ->
            val id = meal.idMeal?.trim().orEmpty()
            if (id.isNotEmpty()) {
                val action = MealsFragmentDirections.actionToDetail(id)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Meal ID tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerView.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        vm.loadMeals(args.categoryName)

        vm.meals.observe(viewLifecycleOwner) { list -> adapter.submitList(list) }
        vm.error.observe(viewLifecycleOwner) { err -> err?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}