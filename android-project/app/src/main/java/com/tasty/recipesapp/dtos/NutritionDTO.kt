package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Nutrition

data class NutritionDTO(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)


fun NutritionDTO.toModel(): Nutrition {
    return Nutrition(
        calories = this.calories ?: 0,
        protein = this.protein ?: 0,
        fat = this.fat ?: 0,
        carbohydrates = this.carbohydrates ?: 0,
        sugar = this.sugar ?: 0,
        fiber = this.fiber ?: 0
    )
}
