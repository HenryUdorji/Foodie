package com.henryudorji.foodie.utils

import com.henryudorji.foodie.FoodieApplication

//
// Created by  on 3/5/2021.
//
object SharedPref {

    fun putStringInPref(key: String, value: String) {
        FoodieApplication.sharedPref.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun putBooleanInPref(key: String, value: Boolean) {
        FoodieApplication.sharedPref.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun getBooleanFromPref(key: String): Boolean {
        return FoodieApplication.sharedPref.getBoolean(key, false)
    }

    fun getStringFromPref(key: String): String {
        return FoodieApplication.sharedPref.getString(key, "")!!
    }
}