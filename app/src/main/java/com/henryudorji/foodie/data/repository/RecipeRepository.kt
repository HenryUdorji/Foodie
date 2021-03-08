package com.henryudorji.foodie.data.repository

import com.henryudorji.foodie.data.remote.ServiceGenerator

//
// Created by  on 3/5/2021.
//
class RecipeRepository {

    suspend fun getAllCategories() = ServiceGenerator.getApi.getCategories()

    //suspend fun getAllIngredients() = ServiceGenerator.getApi.getIngredients()
}