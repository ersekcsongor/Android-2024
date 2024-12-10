package com.tasty.recipesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.repository.RecipeRepository2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository2) : ViewModel() {

    // A MutableStateFlow to hold the list of RecipeEntity objects
    private val _recipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val recipes: StateFlow<List<RecipeEntity>> = _recipes

    init {
        // Fetch all recipes when the ViewModel is initialized
        getAllRecipes()
    }

    // Fetch all recipes from the repository
    private fun getAllRecipes() {
        viewModelScope.launch {
            _recipes.value = repository.getAllRecipes()
        }
    }

    // Insert a new recipe into the database
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
