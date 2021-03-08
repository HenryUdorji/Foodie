package com.henryudorji.foodie.data.remote

import com.henryudorji.foodie.data.model.CategoryResponse
import com.henryudorji.foodie.data.model.IngredientMealResponse
import retrofit2.Response
import retrofit2.http.GET

//
// Created by  on 3/5/2021.
//
interface RecipeApi {

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("list.php?i=list")
    suspend fun getIngredients(): Response<IngredientMealResponse>
}