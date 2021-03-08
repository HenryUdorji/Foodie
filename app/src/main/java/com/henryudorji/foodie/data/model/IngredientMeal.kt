package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class IngredientMeal(
    @SerializedName("idIngredient")
    val idIngredient: String,
    @SerializedName("strDescription")
    val strDescription: String,
    @SerializedName("strIngredient")
    val strIngredient: String,
    @SerializedName("strType")
    val strType: Any
)