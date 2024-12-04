package com.tasty.recipesapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.repository.RecipeRepository2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository2) : ViewModel() {

    // A MutableStateFlow to hold the list of recipes
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    init {
        // Get all recipes when ViewModel is created
        getAllRecipes()
    }

    // Get all recipes from the Room database
    private fun getAllRecipes() {
        viewModelScope.launch {
            _recipes.value = repository.getAllRecipes()
        }
    }

    // Insert a recipe into the database
    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
            getAllRecipes() // Refresh the list after insertion
        }
    }

    // Delete a recipe from the database
    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
            getAllRecipes() // Refresh the list after deletion
        }
    }
}
