package com.henryudorji.foodie.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
): Serializable