package com.tasty.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    fun fetchRecipeData() {
        viewModelScope.launch {
            _recipeList.value = repository.getRecipes()
        }
    }
}
