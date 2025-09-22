package com.arrayyan.mini_project_week05.viewmodel

import androidx.lifecycle.*
import com.arrayyan.mini_project_week05.data.model.Category
import com.arrayyan.mini_project_week05.data.network.NetworkModule
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val repo = MealRepository(NetworkModule.mealApi)

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init { fetchCategories() }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                _categories.value = repo.getCategories() ?: emptyList()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}