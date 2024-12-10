package com.tasty.recipesapp.repository

import com.tasty.recipesapp.entities.RecipeDao
import com.tasty.recipesapp.entities.RecipeEntity

class RecipeRepository2(private val recipeDao: RecipeDao) {

    suspend fun getAllRecipes(): List<RecipeEntity> {
        return recipeDao.getAllRecipes()
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }
}
