package com.tasty.recipesapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.repository.RecipeRepository2
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository2) : ViewModel() {

    // Get all recipes from the Room database
    fun getAllRecipes(): LiveData<List<Recipe>> = liveData {
        emit(repository.getAllRecipes())
    }

    fun insertRecipe(recipe: RecipeEntity) {
        // Launching the suspend function in a coroutine scope
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    // Delete a recipe from the database
    fun deleteRecipe(recipe: RecipeEntity) {
        // Launching the suspend function in a coroutine scope
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
}
