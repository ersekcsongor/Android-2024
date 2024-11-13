package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Measurement

// MeasurementDTO.kt
data class MeasurementDTO(
    val quantity: String,
    val unit: UnitDTO
)

