package com.tasty.recipesapp.repository

import com.tasty.recipesapp.entities.RecipeDao
import com.tasty.recipesapp.models.Recipe
import com.google.gson.Gson
import com.tasty.recipesapp.entities.RecipeEntity

class RecipeRepository2(private val recipeDao: RecipeDao) {

    suspend fun getAllRecipes(): List<Recipe> {
        return recipeDao.getAllRecipes().map { entity ->
            Gson().fromJson(entity.json, Recipe::class.java)
        }
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }
}
