package com.tasty.recipesapp.repository

import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.service.RecipeService

class RecipeRepository(private val recipeService: RecipeService) {
    suspend fun getRecipesFromApi(): List<Recipe> {
        val response = recipeService.getRecipes()
        return response // Or directly return `response` if returning `List<Recipe>`.
    }

}
