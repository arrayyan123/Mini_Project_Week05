package com.arrayyan.mini_project_week05.viewmodel

import androidx.lifecycle.*
import com.arrayyan.mini_project_week05.data.model.MealDetail
import com.arrayyan.mini_project_week05.data.network.MealApi
import com.arrayyan.mini_project_week05.data.repository.MealRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val repository = MealRepository(MealApi.create())

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
                e.printStackTrace()
                _randomMeal.value = null
            }
        }
    }
}
