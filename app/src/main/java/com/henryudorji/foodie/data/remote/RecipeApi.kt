package com.henryudorji.foodie.data.remote

import com.henryudorji.foodie.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//
// Created by  on 3/5/2021.
//
interface RecipeApi {

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getCategoryMeals(
        @Query("c") meal: String
    ): Response<CategoryMealResponse>

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): Response<MealResponse>

}