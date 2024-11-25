package com.tasty.recipesapp.repository

import com.google.gson.Gson
import com.tasty.recipesapp.dtos.RecipeDTO
import com.tasty.recipesapp.entities.RecipeDao
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.Recipe
import org.json.JSONObject

class RecipeRepository2(private val recipeDao: RecipeDao) {
    private val gson = Gson() // Initialize Gson instance

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun getAllRecipes(): List<Recipe> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }
    }

    suspend fun getRecipeById(id: Long): Recipe? {
        val recipeEntity = recipeDao.getRecipeById(id) ?: return null
        val jsonObject = JSONObject(recipeEntity.json)
        jsonObject.put("id", recipeEntity.internalId)
        return gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }
}
