package com.henryudorji.foodie.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.henryudorji.foodie.R
import com.henryudorji.foodie.databinding.FragmentSplashBinding
import com.henryudorji.foodie.ui.MainActivity

//
// Created by  on 3/4/2021.
//
class SplashFragment: Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var handler: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        val activityMainBinding = (activity as MainActivity).binding
        activityMainBinding.toolbar.visibility = View.GONE

        //TODO"Remove status bar"
        handler = Handler()
        handler.apply {
            postDelayed(Runnable {
                findNavController().navigate(
                    R.id.action_splashFragment_to_homeFragment
                )
            }, 2000)
        }

    }
}