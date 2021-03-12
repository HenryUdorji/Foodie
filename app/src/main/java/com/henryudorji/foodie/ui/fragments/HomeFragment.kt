package com.henryudorji.foodie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.henryudorji.foodie.R
import com.henryudorji.foodie.adapter.HomeAdapter
import com.henryudorji.foodie.databinding.FragmentHomeBinding
import com.henryudorji.foodie.ui.MainActivity
import com.henryudorji.foodie.ui.RecipeViewModel
import com.henryudorji.foodie.utils.Constants.CATEGORY
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
        val activityMainBinding = (activity as MainActivity).binding

        val toolbar = activityMainBinding.toolbar
        toolbar.visibility = View.VISIBLE
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)

        activityMainBinding.lottieSwitch.visibility = View.VISIBLE
        activityMainBinding.toolbarText.apply {
            visibility = View.VISIBLE
            text = getString(R.string.app_name)
        }

        setupRecyclerView()
        showCategories()
    }

    private fun showCategories() {
        recipeViewModel.categories.observe(viewLifecycleOwner, Observer { response ->
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
        binding.noNetworkLayout.visibility = View.GONE
    }

    private fun showNoNetworkView(message: String) {
        binding.noNetworkLayout.visibility = View.VISIBLE
        binding.networkMessage.text = message
        binding.retryBtn.setOnClickListener {
            recipeViewModel.getAllCategories()
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
            val bundle = Bundle().apply {
                putSerializable(CATEGORY, it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_categoryMealFragment,
                bundle
            )
        }
    }
}