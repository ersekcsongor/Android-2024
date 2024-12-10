package com.tasty.recipesapp.dtos

data class RecipeModelDTO(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val originalVideoUrl: String,
)
