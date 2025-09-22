package com.arrayyan.mini_project_week05.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.arrayyan.mini_project_week05.databinding.FragmentCategoriesBinding
import com.arrayyan.mini_project_week05.viewmodel.CategoriesViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arrayyan.mini_project_week05.data.model.Category
import com.arrayyan.mini_project_week05.databinding.ItemMealBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.title = "Categories"

        val adapter = CategoryAdapter { category ->
            val name = category.strCategory?.trim().orEmpty()
            if (name.isNotEmpty()) {
                val action = CategoriesFragmentDirections.actionToMeals(name)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Kategori tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerView.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        vm.categories.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
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

/**
 * CategoryAdapter menggunakan ItemMealBinding (supaya konsisten layout).
 */
class CategoryAdapter(private val onClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryAdapter.VH>(DIFF) {

    class VH(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.binding.title.text = item.strCategory
        Glide.with(holder.itemView)
            .load(item.strCategoryThumb)
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.darker_gray)
            .into(holder.binding.thumb)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category) =
                oldItem.idCategory == newItem.idCategory

            override fun areContentsTheSame(oldItem: Category, newItem: Category) =
                oldItem == newItem
        }
    }
}