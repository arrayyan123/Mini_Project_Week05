package com.arrayyan.mini_project_week05.viewmodel

import androidx.lifecycle.*
import com.arrayyan.mini_project_week05.data.model.MealSummary
import com.arrayyan.mini_project_week05.data.network.NetworkModule
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {
    private val repo = MealRepository(NetworkModule.mealApi)
    private val _meals = MutableLiveData<List<MealSummary>>()
    val meals: LiveData<List<MealSummary>> = _meals
    val error = MutableLiveData<String?>()

    fun loadMeals(category: String) {
        viewModelScope.launch {
            try {
                _meals.value = repo.getMealsByCategory(category) ?: emptyList()
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}
