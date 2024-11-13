package com.tasty.recipesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.dtos.RecipeDTO
import com.tasty.recipesapp.models.Recipe

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    // Method to load recipes
    fun loadRecipes(recipeDTOs: List<RecipeDTO>) {
        _recipes.value = repository.getRecipes(recipeDTOs)
    }
}
