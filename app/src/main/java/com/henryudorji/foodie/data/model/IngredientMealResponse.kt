package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class IngredientMealResponse(
    @SerializedName("meals")
    val ingredientMeals: List<IngredientMeal>
)