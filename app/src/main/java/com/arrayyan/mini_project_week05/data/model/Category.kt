package com.arrayyan.mini_project_week05.data.model

data class CategoryResponse(
    val categories: List<Category>?
)

data class Category(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryThumb: String?,
    val strCategoryDescription: String?
)
