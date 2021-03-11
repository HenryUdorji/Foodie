package com.henryudorji.foodie.data.repository

import com.henryudorji.foodie.data.remote.ServiceGenerator

//
// Created by  on 3/5/2021.
//
class RecipeRepository {

    suspend fun getAllCategories() = ServiceGenerator.getApi.getCategories()

    suspend fun getAllCategoryMeals(meal: String) = ServiceGenerator.getApi.getCategoryMeals(meal)

    suspend fun getMealDetails(mealId: String) = ServiceGenerator.getApi.getMealDetails(mealId)
}