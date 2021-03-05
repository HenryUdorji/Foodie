package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("meals")
    val meals: List<MealXX>
)