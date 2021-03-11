package com.henryudorji.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.henryudorji.foodie.data.model.Category
import com.henryudorji.foodie.data.model.CategoryMeal
import com.henryudorji.foodie.databinding.CategoryMealsListBinding
import com.henryudorji.foodie.databinding.RecyclerListBinding

//
// Created by  on 3/5/2021.
//
class CategoryMealsAdapter: RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewHolder>() {

    inner class CategoryMealsViewHolder(var binding: CategoryMealsListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        val binding = CategoryMealsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryMealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        val categoryMeal = differ.currentList[position]

        holder.binding.apply {
            Glide.with(root)
                .load(categoryMeal.strMealThumb)
                .into(recyclerImage)

            recyclerText.text = categoryMeal.strMeal

            root.setOnClickListener {
                onItemClickListener?.let { it(categoryMeal) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object: DiffUtil.ItemCallback<CategoryMeal>() {
        override fun areItemsTheSame(oldItem: CategoryMeal, newItem: CategoryMeal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: CategoryMeal, newItem: CategoryMeal): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((CategoryMeal) -> Unit) ? = null

    fun setOnItemClickListener(listener: (CategoryMeal) -> Unit) {
        onItemClickListener = listener
    }
}