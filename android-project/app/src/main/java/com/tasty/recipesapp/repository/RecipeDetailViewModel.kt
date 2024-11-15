package com.tasty.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.repository.RecipeRepository

class RecipeDetailViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipeDetail = MutableLiveData<Recipe?>()
    val recipeDetail: LiveData<Recipe?> get() = _recipeDetail

    // Fetch the recipe by ID and update LiveData
    fun fetchRecipeDetail(recipeId: Int) {
        _recipeDetail.value = repository.getRecipeById(recipeId)
    }
}
