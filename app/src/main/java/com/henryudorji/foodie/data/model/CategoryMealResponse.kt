package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class CategoryMealResponse(
    @SerializedName("meals")
    val categoryMeals: List<CategoryMeal>
)