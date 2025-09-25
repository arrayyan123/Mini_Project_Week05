package com.arrayyan.mini_project_week05.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrayyan.mini_project_week05.data.model.MealDetail
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MealRepository
) : ViewModel() {

    private val _randomMeal = MutableLiveData<MealDetail?>()
    val randomMeal: LiveData<MealDetail?> = _randomMeal

    init {
        fetchRandomMeal()
    }

    fun fetchRandomMeal() {
        viewModelScope.launch {
            try {
                _randomMeal.value = repository.getRandomMeal()
            } catch (e: Exception) {
                _randomMeal.value = null
            }
        }
    }
}
