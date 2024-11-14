package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.Component
import com.tasty.recipesapp.models.Ingredient
import com.tasty.recipesapp.models.Measurement
import com.tasty.recipesapp.models.UnitModel

data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientDTO,
    val measurement: MeasurementDTO,
    val position: Int
)


fun ComponentDTO.toModel(): Component {
    return Component(
        rawText = this.rawText,
        extraComment = this.extraComment,
        ingredient = Ingredient(this.ingredient.name), // Assuming Ingredient name exists
        measurement = Measurement(
            quantity = this.measurement.quantity,
            unit = UnitModel(
                name = this.measurement.unit.name,
                singular = this.measurement.unit.displaySingular,
                plural = this.measurement.unit.displayPlural,
                abbreviation = this.measurement.unit.abbreviation
            )
        ),
        position = this.position
    )
}
