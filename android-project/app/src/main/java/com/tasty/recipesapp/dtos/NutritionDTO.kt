package com.tasty.recipesapp.dtos

data class NutritionDTO(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)