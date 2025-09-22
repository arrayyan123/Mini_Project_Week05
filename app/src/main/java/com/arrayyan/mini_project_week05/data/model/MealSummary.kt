package com.arrayyan.mini_project_week05.data.model

data class MealsResponse(
    val meals: List<MealSummary>?
)

data class MealSummary(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)