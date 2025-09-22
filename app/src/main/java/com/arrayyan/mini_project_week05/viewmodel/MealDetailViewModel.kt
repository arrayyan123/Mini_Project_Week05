package com.arrayyan.mini_project_week05.viewmodel

import androidx.lifecycle.*
import com.arrayyan.mini_project_week05.data.model.MealDetail
import com.arrayyan.mini_project_week05.data.network.NetworkModule
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import kotlinx.coroutines.launch

class MealDetailViewModel : ViewModel() {

    private val repo = MealRepository(NetworkModule.mealApi)

    // LiveData untuk detail meal
    private val _meal = MutableLiveData<MealDetail?>()
    val meal: LiveData<MealDetail?> = _meal

    // LiveData untuk error
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadDetail(mealId: String) {
        viewModelScope.launch {
            try {
                val result = repo.getMealDetail(mealId)
                _meal.value = result
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}