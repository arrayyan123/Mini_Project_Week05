package com.arrayyan.mini_project_week05.data.repository


import com.arrayyan.mini_project_week05.data.model.*
import com.arrayyan.mini_project_week05.data.network.MealApi

class MealRepository(private val api: MealApi) {
    suspend fun getCategories(): List<Category>? {
        return api.getCategories().categories
    }

    suspend fun getMealsByCategory(category: String): List<MealSummary>? {
        return api.getMealsByCategory(category).meals
    }

    suspend fun getMealDetail(id: String): MealDetail? {
        return api.getMealDetail(id).meals?.firstOrNull()
    }
}