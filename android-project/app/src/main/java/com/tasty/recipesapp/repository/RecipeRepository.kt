package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.dtos.RecipeDTO
import com.tasty.recipesapp.dtos.toModel
import com.tasty.recipesapp.models.Recipe

class RecipeRepository(private val context: Context) {

    // Load and parse JSON data
    fun loadRecipesFromJson(): List<RecipeDTO> {
        val json = context.assets.open("multiple_recipes.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<RecipeDTO>>() {}.type
        return Gson().fromJson(json, type)
    }

    // Add this method to RecipeRepository
    fun getRecipeById(recipeId: Int): Recipe? {
        return getRecipes().find { it.id == recipeId }
    }

    // Fetch recipes as model objects
    fun getRecipes(): List<Recipe> {
        return loadRecipesFromJson().map { it.toModel() }
    }
}

// Extension function to map RecipeDTO to Recipe
fun RecipeDTO.toModel(): Recipe {
    return Recipe(
        id = this.recipeID,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        keywords = this.keywords.split(", ").map { it.trim() },
        isPublic = this.isPublic,
        userEmail = this.userEmail,
        originalVideoUrl = this.originalVideoUrl,
        country = this.country,
        numServings = this.numServings,
        components = this.components ,// Convert ComponentDTO to Component
        instructions = this.instructions, // Convert InstructionDTO to Instruction
        nutrition = this.nutrition?.toModel() // Convert NutritionDTO to Nutrition


    )
}
