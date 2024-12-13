package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Component
import com.tasty.recipesapp.models.Ingredient


data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: String, // Only store ingredient name
    val quantity: String,
    val unit: String // Conc
)


fun ComponentDTO.toModel(): Component {
    return Component(
        rawText = this.rawText,
        extraComment = this.extraComment,
        ingredient = Ingredient(name = this.ingredient), // Transform String to Ingredient object
        quantity = this.quantity,
        unit = this.unit
    )
}
