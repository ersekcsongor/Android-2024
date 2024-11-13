package com.tasty.recipesapp.models

// Component.kt
data class Component(
    val rawText: String,
    val extraComment: String,
    val ingredient: Ingredient,
    val measurement: Measurement,
    val position: Int
)