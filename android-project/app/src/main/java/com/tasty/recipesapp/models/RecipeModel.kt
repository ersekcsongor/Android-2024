package com.tasty.recipesapp.models

import com.tasty.recipesapp.dtos.RecipeModelDTO

data class RecipeModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val originalVideoUrl: String,
)


fun RecipeModelDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        originalVideoUrl = this.originalVideoUrl
    )
}

