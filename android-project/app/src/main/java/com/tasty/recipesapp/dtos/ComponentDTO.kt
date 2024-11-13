package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Component

data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientDTO,
    val measurement: MeasurementDTO,
    val position: Int
)
