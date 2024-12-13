package com.tasty.recipesapp.models

// Component.kt

data class Component(
    val rawText: String,
    val extraComment: String,
    val ingredient: Ingredient, // Only store ingredient name
    val quantity: String,
    val unit: String // Concatenate unit details
)