package com.henryudorji.foodie.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.henryudorji.foodie.FoodieApplication
import com.henryudorji.foodie.data.model.CategoryResponse
import com.henryudorji.foodie.data.model.IngredientMealResponse
import com.henryudorji.foodie.data.repository.RecipeRepository
import com.henryudorji.foodie.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

//
// Created by  on 3/5/2021.
//
class RecipeViewModel(
    application: Application,
    private val recipeRepository: RecipeRepository
): AndroidViewModel(application) {

    val mealsCategories: MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()
    val ingredients: MutableLiveData<Resource<IngredientMealResponse>> = MutableLiveData()

    init {
        getAllMealCategories()
        //getAllIngredientMeal()
    }

    fun getAllMealCategories() = viewModelScope.launch {
        mealsCategories.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = recipeRepository.getAllCategories()
                mealsCategories.postValue(handleMealsCategories(response))
            }else {
                mealsCategories.postValue(Resource.Error("Internet connection unavailable, check connection and retry"))
            }
        }catch (t: Throwable) {
            when(t) {
                is IOException -> mealsCategories.postValue(Resource.Error("Network failure"))
                else -> mealsCategories.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    /*fun getAllIngredientMeal() = viewModelScope.launch {
        ingredients.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = recipeRepository.getAllIngredients()
                ingredients.postValue(handleIngredientResponse(response))
            }else {
                ingredients.postValue(Resource.Error("Internet connection unavailable, check connection and retry"))
            }
        }catch (t: Throwable) {
            when(t) {
                is IOException -> mealsCategories.postValue(Resource.Error("Network failure"))
                else -> mealsCategories.postValue(Resource.Error("Conversion Error"))
            }
        }
    }*/

    private fun handleIngredientResponse(response: Response<IngredientMealResponse>): Resource<IngredientMealResponse>? {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it);
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleMealsCategories(response: Response<CategoryResponse>): Resource<CategoryResponse> {
        if (response.isSuccessful) {
            response.body()?.let { categoriesResponse ->
                return Resource.Success(categoriesResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<FoodieApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}