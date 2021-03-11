package com.henryudorji.foodie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.henryudorji.foodie.R
import com.henryudorji.foodie.adapter.CategoryMealsAdapter
import com.henryudorji.foodie.databinding.FragmentCategoryMealsBinding
import com.henryudorji.foodie.ui.MainActivity
import com.henryudorji.foodie.ui.RecipeViewModel
import com.henryudorji.foodie.utils.Constants
import com.henryudorji.foodie.utils.Resource

//
// Created by  on 3/4/2021.
//
class CategoryMealsFragment: Fragment(R.layout.fragment_category_meals) {
    private val TAG = "CategoryDetailFragment"
    private val args: CategoryMealsFragmentArgs by navArgs()
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var binding: FragmentCategoryMealsBinding
    private lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryMealsBinding.bind(view)
        val binding1 = (activity as MainActivity).binding
        recipeViewModel = (activity as MainActivity).recipeViewModel

        val categoryName = args.category.strCategory
        binding1.toolbarText.visibility = View.VISIBLE
        binding1.toolbarText.text = categoryName
        binding1.lottieSwitch.visibility = View.GONE


        setupRecyclerView()
        showCategoryMeal(categoryName)
    }

    private fun setupRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryMealsAdapter
        }
        categoryMealsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString(Constants.MEAL_ID, it.idMeal)
            }
            findNavController().navigate(
                R.id.action_categoryMealFragment_to_mealDetailFragment,
                bundle
            )
        }

    }

    private fun showCategoryMeal(categoryName: String) {
        recipeViewModel.getAllCategoryMeals(categoryName)
        recipeViewModel.categoryMeals.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideNoNetworkView()
                    response.data?.let {
                        categoryMealsAdapter.differ.submitList(it.categoryMeals)
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
        binding.noNetworkLayout.visibility = View.GONE
    }

    private fun showNoNetworkView(message: String) {
        binding.noNetworkLayout.visibility = View.VISIBLE
        binding.networkMessage.text = message
        binding.retryBtn.setOnClickListener {
            recipeViewModel.getAllCategories()
        }
    }
}