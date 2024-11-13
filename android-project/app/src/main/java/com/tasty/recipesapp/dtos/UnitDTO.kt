package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.UnitModel

// UnitDTO.kt
data class UnitDTO(
    val name: String,
    val displaySingular: String,
    val displayPlural: String,
    val abbreviation: String
)


