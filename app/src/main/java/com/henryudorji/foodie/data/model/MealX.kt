package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class MealX(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)