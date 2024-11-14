package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Instruction

data class InstructionDTO(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)
fun InstructionDTO.toModel(): Instruction {
    return Instruction(
        id = this.instructionID,
        text = this.displayText,
        position = this.position
    )
}
