package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Instruction

data class InstructionDTO(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)
