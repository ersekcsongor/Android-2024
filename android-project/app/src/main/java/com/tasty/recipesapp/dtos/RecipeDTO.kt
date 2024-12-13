package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Component
import com.tasty.recipesapp.models.Instruction
import com.tasty.recipesapp.models.Nutrition

data class RecipeDto(
    val recipeID: Int?,                // Nullable
    val name: String?,                 // Nullable
    val description: String?,          // Nullable
    val thumbnailUrl: String?,         // Nullable
    val keywords: String?,             // CSV String, split into List<String>
    val isPublic: Boolean?,            // Nullable
    val userEmail: String?,            // Nullable
    val originalVideoUrl: String?,     // Nullable
    val country: String?,              // Nullable
    val numServings: Int?,             // Nullable
    val components: List<ComponentDTO>?,  // Nullable list
    val instructions: List<InstructionDTO>?, // Nullable list
    val nutrition: NutritionDTO?       // Nullable
)

data class RecipeResponse(
    val recipes: List<RecipeDto>
)
