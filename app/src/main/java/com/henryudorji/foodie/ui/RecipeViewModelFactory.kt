package com.henryudorji.foodie.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.henryudorji.foodie.data.repository.RecipeRepository

//
// Created by  on 3/5/2021.
//
class RecipeViewModelFactory(
    private val application: Application,
    private val recipeRepository: RecipeRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(application, recipeRepository) as T
    }
}