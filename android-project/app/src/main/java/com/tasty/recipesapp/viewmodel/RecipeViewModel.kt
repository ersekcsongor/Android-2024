package com.tasty.recipesapp.viewmodel

import RecipeApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tasty.recipesapp.models.Recipe
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _rawJson = MutableLiveData<String>()
    val rawJson: LiveData<String> get() = _rawJson

    private val recipeApiClient = RecipeApiClient()

    fun getAllRecipesFromApi() {
        viewModelScope.launch {
            try {
                // Fetch recipes directly as a list
                val response = recipeApiClient.getRecipes()

                // Log raw JSON for debugging (if needed)
                _rawJson.value = Gson().toJson(response)

                // Update LiveData with the fetched list
                _recipes.value = response?: emptyList()
            } catch (e: Exception) {
                Log.e("RecipeListViewModel", "Error fetching recipes", e)
            }
        }
    }


}
