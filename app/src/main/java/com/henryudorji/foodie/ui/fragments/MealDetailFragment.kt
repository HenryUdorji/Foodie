package com.henryudorji.foodie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.henryudorji.foodie.R
import com.henryudorji.foodie.data.model.Meal
import com.henryudorji.foodie.databinding.FragmentMealDetailBinding
import com.henryudorji.foodie.ui.MainActivity
import com.henryudorji.foodie.ui.RecipeViewModel
import com.henryudorji.foodie.utils.Resource

//
// Created by  on 3/4/2021.
//
class MealDetailFragment: Fragment(R.layout.fragment_meal_detail) {

    private lateinit var binding: FragmentMealDetailBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private val args: MealDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMealDetailBinding.bind(view)

        recipeViewModel = (activity as MainActivity).recipeViewModel
        val mealId = args.mealId
        val binding1 = (activity as MainActivity).binding
        binding1.toolbarText.visibility = View.GONE
        binding1.lottieSwitch.visibility = View.GONE
        binding1.bottomNavigationView.visibility = View.GONE

        showMealDetail(mealId)
    }

    private fun showMealDetail(mealId: String) {
        recipeViewModel.getMealDetails(mealId)
        recipeViewModel.mealDetails.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Glide.with(requireActivity())
                            .load(it.meals[0].strMealThumb)
                            .into(binding.mealImage)

                        binding.mealName.text = it.meals[0].strMeal
                        binding.categoryName.text = it.meals[0].strCategory
                        binding.countryName.text = it.meals[0].strArea
                        binding.instructionText.text = it.meals[0].strInstructions
                        setIngredientsAndMeasures(it.meals[0])
                        binding.youtubeBtn.apply {
                            isEnabled = true
                            setOnClickListener { view ->
                                Snackbar.make(binding.root, it.meals[0].strYoutube, Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
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

    private fun setIngredientsAndMeasures(meal: Meal) {
        val ingredientText = binding.ingredientText
        val measureText = binding.measuresText

        if (meal.strIngredient1.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient1}")
        }
        if (meal.strIngredient2.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient2}")
        }
        if (meal.strIngredient3.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient3}")
        }
        if (meal.strIngredient4.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient4}")
        }
        if (meal.strIngredient5.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient5}")
        }
        if (meal.strIngredient6.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient6}")
        }
        if (meal.strIngredient7.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient7}")
        }
        if (meal.strIngredient8.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient8}")
        }
        if (meal.strIngredient9.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient9}")
        }
        if (meal.strIngredient10.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient10}")
        }
        if (meal.strIngredient11.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient11}")
        }
        if (meal.strIngredient12.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient12}")
        }
        if (meal.strIngredient13.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient13}")
        }
        if (meal.strIngredient14.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient14}")
        }
        if (meal.strIngredient15.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient15}")
        }
        if (meal.strIngredient16.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient16}")
        }
        if (meal.strIngredient17.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient17}")
        }
        if (meal.strIngredient18.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient18}")
        }
        if (meal.strIngredient19.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient19}")
        }
        if (meal.strIngredient20.isNotEmpty()) {
            ingredientText.append("\n \u2022 ${meal.strIngredient20}")
        }



        if (meal.strMeasure1.isNotEmpty() && !Character.isWhitespace(meal.strMeasure1[0])) {
            measureText.append("\n : ${meal.strMeasure1}")
        }
        if (meal.strMeasure2.isNotEmpty() && !Character.isWhitespace(meal.strMeasure2[0])) {
            measureText.append("\n : ${meal.strMeasure2}")
        }
        if (meal.strMeasure3.isNotEmpty() && !Character.isWhitespace(meal.strMeasure3[0])) {
            measureText.append("\n : ${meal.strMeasure3}")
        }
        if (meal.strMeasure4.isNotEmpty() && !Character.isWhitespace(meal.strMeasure4[0])) {
            measureText.append("\n : ${meal.strMeasure4}")
        }
        if (meal.strMeasure5.isNotEmpty() && !Character.isWhitespace(meal.strMeasure5[0])) {
            measureText.append("\n : ${meal.strMeasure5}")
        }
        if (meal.strMeasure6.isNotEmpty() && !Character.isWhitespace(meal.strMeasure6[0])) {
            measureText.append("\n : ${meal.strMeasure6}")
        }
        if (meal.strMeasure7.isNotEmpty() && !Character.isWhitespace(meal.strMeasure7[0])) {
            measureText.append("\n : ${meal.strMeasure7}")
        }
        if (meal.strMeasure8.isNotEmpty() && !Character.isWhitespace(meal.strMeasure8[0])) {
            measureText.append("\n : ${meal.strMeasure8}")
        }
        if (meal.strMeasure9.isNotEmpty() && !Character.isWhitespace(meal.strMeasure9[0])) {
            measureText.append("\n : ${meal.strMeasure9}")
        }
        if (meal.strMeasure10.isNotEmpty() && !Character.isWhitespace(meal.strMeasure10[0])) {
            measureText.append("\n : ${meal.strMeasure10}")
        }
        if (meal.strMeasure11.isNotEmpty() && !Character.isWhitespace(meal.strMeasure11[0])) {
            measureText.append("\n : ${meal.strMeasure11}")
        }
        if (meal.strMeasure12.isNotEmpty() && !Character.isWhitespace(meal.strMeasure12[0])) {
            measureText.append("\n : ${meal.strMeasure12}")
        }
        if (meal.strMeasure13.isNotEmpty() && !Character.isWhitespace(meal.strMeasure13[0])) {
            measureText.append("\n : ${meal.strMeasure13}")
        }
        if (meal.strMeasure14.isNotEmpty() && !Character.isWhitespace(meal.strMeasure14[0])) {
            measureText.append("\n : ${meal.strMeasure14}")
        }
        if (meal.strMeasure15.isNotEmpty() && !Character.isWhitespace(meal.strMeasure15[0])) {
            measureText.append("\n : ${meal.strMeasure15}")
        }
        if (meal.strMeasure16.isNotEmpty() && !Character.isWhitespace(meal.strMeasure16[0])) {
            measureText.append("\n : ${meal.strMeasure16}")
        }
        if (meal.strMeasure17.isNotEmpty() && !Character.isWhitespace(meal.strMeasure17[0])) {
            measureText.append("\n : ${meal.strMeasure17}")
        }
        if (meal.strMeasure18.isNotEmpty() && !Character.isWhitespace(meal.strMeasure18[0])) {
            measureText.append("\n : ${meal.strMeasure18}")
        }
        if (meal.strMeasure19.isNotEmpty() && !Character.isWhitespace(meal.strMeasure19[0])) {
            measureText.append("\n : ${meal.strMeasure19}")
        }
        if (meal.strMeasure20.isNotEmpty() && !Character.isWhitespace(meal.strMeasure20[0])) {
            measureText.append("\n : ${meal.strMeasure20}")
        }
    }
}