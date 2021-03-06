package com.henryudorji.foodie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.airbnb.lottie.LottieAnimationView
import com.henryudorji.foodie.R
import com.henryudorji.foodie.data.repository.RecipeRepository
import com.henryudorji.foodie.databinding.ActivityMainBinding
import com.henryudorji.foodie.utils.Constants.IS_SWITCH_ON
import com.henryudorji.foodie.utils.SharedPref

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var lottieSwitch: LottieAnimationView
    private var isSwitchOn = false
    lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isSwitchOn = SharedPref.getBooleanFromPref(IS_SWITCH_ON)

        val recipeRepository = RecipeRepository()
        val recipeViewModelFactory = RecipeViewModelFactory(application, recipeRepository)
        recipeViewModel = ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
        val navController = navHostFragment.navController
        binding.toolbar.setupWithNavController(navController)

        //lottie switch
        lottieSwitch = binding.lottieSwitch

        if (isSwitchOn) {
            lottieSwitch.setMinAndMaxProgress(0.5f, 1.0f)
            lottieSwitch.playAnimation()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            lottieSwitch.setMinAndMaxProgress(0.0f, 0.5f)
            lottieSwitch.playAnimation()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        lottieSwitch.setOnClickListener {
            if (isSwitchOn) {
                lottieSwitch.setMinAndMaxProgress(0.5f, 1.0f)
                lottieSwitch.playAnimation()
                SharedPref.putBooleanInPref(IS_SWITCH_ON, false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreate()
            }else {
                lottieSwitch.setMinAndMaxProgress(0.0f, 0.5f)
                lottieSwitch.playAnimation()
                SharedPref.putBooleanInPref(IS_SWITCH_ON, true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreate()
            }
        }

    }
}