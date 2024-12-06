package com.tasty.recipesapp.models

// Recipe.kt
data class RecipeModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val originalVideoUrl: String,
    )
