package com.tasty.recipesapp.models

import com.tasty.recipesapp.dtos.RecipeDto

data class Recipe(
    val recipeID: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<Component>,
    val instructions: List<Instruction>,
    val nutrition: Nutrition?
)

// Extension Function to Map DTO to Model
fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        recipeID = this.recipeID ?: 0, // Provide default value if null
        name = this.name ?: "", // Provide default value if null
        description = this.description ?: "",
        thumbnailUrl = this.thumbnailUrl ?: "",
        keywords = this.keywords.toString(), // Safely split CSV to list
        isPublic = this.isPublic ?: false,
        userEmail = this.userEmail ?: "",
        originalVideoUrl = this.originalVideoUrl ?: "",
        country = this.country ?: "",
        numServings = this.numServings ?: 0,
        components = this.components?.map { componentDto ->
            Component(
                rawText = componentDto.rawText ?: "",
                extraComment = componentDto.extraComment ?: "",
                ingredient = componentDto.ingredient?.let {
                    Ingredient(name = it ?: "")
                } ?: Ingredient(name = ""),
                quantity = componentDto.quantity ?: "",
                unit = componentDto.unit ?: "")
        } ?: emptyList(),
        instructions = this.instructions?.map { instructionDto ->
            Instruction(
                instructionID = instructionDto.instructionID ?: 0,
                displayText = instructionDto.displayText ?: "",
                position = instructionDto.position ?: 0
            )
        } ?: emptyList(),
        nutrition = this.nutrition?.let {
            Nutrition(
                calories = it.calories ?: 0,
                protein = it.protein ?: 0,
                fat = it.fat ?: 0,
                carbohydrates = it.carbohydrates ?: 0,
                sugar = it.sugar ?: 0,
                fiber = it.fiber ?: 0
            )
        }
    )
}
