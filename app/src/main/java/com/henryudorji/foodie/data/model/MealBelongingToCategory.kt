package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class MealBelongingToCategory(
    @SerializedName("meals")
    val meals: List<MealX>
)