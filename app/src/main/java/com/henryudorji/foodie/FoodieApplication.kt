package com.henryudorji.foodie

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import com.henryudorji.foodie.utils.Constants.IS_SWITCH_ON
import com.henryudorji.foodie.utils.SharedPref

//
// Created by  on 3/5/2021.
//
class FoodieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        if (SharedPref.getBooleanFromPref(IS_SWITCH_ON)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        lateinit var sharedPref: SharedPreferences
    }
}