package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName

data class Random(
    @SerializedName("meals")
    val meals: List<Meal>
)