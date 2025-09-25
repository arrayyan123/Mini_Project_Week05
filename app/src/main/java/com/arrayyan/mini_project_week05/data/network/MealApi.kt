package com.arrayyan.mini_project_week05.data.network

import com.arrayyan.mini_project_week05.data.model.*
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealDetailResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id: String): MealDetailResponse

    companion object {
        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

        fun create(): MealApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MealApi::class.java)
        }
    }
}