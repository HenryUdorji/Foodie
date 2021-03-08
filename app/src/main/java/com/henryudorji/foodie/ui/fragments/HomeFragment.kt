package com.henryudorji.foodie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.henryudorji.foodie.R
import com.henryudorji.foodie.adapter.HomeAdapter
import com.henryudorji.foodie.databinding.FragmentHomeBinding
import com.henryudorji.foodie.ui.MainActivity
import com.henryudorji.foodie.ui.RecipeViewModel
import com.henryudorji.foodie.utils.Resource

//
// Created by  on 3/4/2021.
//
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recipeViewModel: RecipeViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        recipeViewModel = (activity as MainActivity).recipeViewModel
        setupRecyclerView()
        showMealCategories()
        //showIngredients()
    }

    /*private fun showIngredients() {
        recipeViewModel.ingredients.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideNoNetworkView()
                    response.data?.let {
                        homeAdapter.differ.
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        })
    }*/

    private fun showMealCategories() {
        recipeViewModel.mealsCategories.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideNoNetworkView()
                    response.data?.let {
                        homeAdapter.differ.submitList(it.categories)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                        showNoNetworkView(message)
                        Log.e(TAG, "ERROR ->: $message")
                    }
                }
                is Resource.Loading -> {
                    hideNoNetworkView()
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideNoNetworkView() {
        /*binding.recyclerText.visibility = View.VISIBLE
        binding.recyclerText2.visibility = View.VISIBLE*/

        binding.noNetworkLayout.visibility = View.GONE
    }

    private fun showNoNetworkView(message: String) {
        /*binding.recyclerText.visibility = View.GONE
        binding.recyclerText2.visibility = View.GONE*/

        binding.noNetworkLayout.visibility = View.VISIBLE
        binding.networkMessage.text = message
        binding.retryBtn.setOnClickListener {
            recipeViewModel.getAllMealCategories()
        }
    }


    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.categoryRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL,
                false)
            adapter = homeAdapter
        }
        homeAdapter.setOnItemClickListener {
            Snackbar.make(binding.root, "Clicked", Snackbar.LENGTH_SHORT).show()
        }
    }
}