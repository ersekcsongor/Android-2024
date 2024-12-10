package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Component


data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val position: Int
)


fun ComponentDTO.toModel(): Component {
    return Component(
        rawText = this.rawText,
        extraComment = this.extraComment,
        position = this.position
    )
}
