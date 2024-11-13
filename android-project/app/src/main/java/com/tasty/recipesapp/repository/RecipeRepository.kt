package com.tasty.recipesapp.repository
import com.tasty.recipesapp.dtos.ComponentDTO
import com.tasty.recipesapp.dtos.InstructionDTO
import com.tasty.recipesapp.dtos.NutritionDTO
import com.tasty.recipesapp.dtos.RecipeDTO
import com.tasty.recipesapp.models.Component
import com.tasty.recipesapp.models.Ingredient
import com.tasty.recipesapp.models.Instruction
import com.tasty.recipesapp.models.Measurement
import com.tasty.recipesapp.models.Nutrition
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.models.UnitModel

class RecipeRepository {

    fun mapToRecipeModel(recipeDTO: RecipeDTO): Recipe {
        return Recipe(
            id = recipeDTO.recipeID,
            name = recipeDTO.name,
            description = recipeDTO.description,
            thumbnailUrl = recipeDTO.thumbnailUrl,
            keywords = recipeDTO.keywords.split(", "), // Split keywords into a list
            isPublic = recipeDTO.isPublic,
            userEmail = recipeDTO.userEmail,
            originalVideoUrl = recipeDTO.originalVideoUrl,
            country = recipeDTO.country,
            numServings = recipeDTO.numServings,
            components = recipeDTO.components.map { mapToComponentModel(it) },
            instructions = recipeDTO.instructions.map { mapToInstructionModel(it) },
            nutrition = mapToNutritionModel(recipeDTO.nutrition)
        )
    }

    private fun mapToComponentModel(componentDTO: ComponentDTO): Component {
        return Component(
            rawText = componentDTO.rawText,
            extraComment = componentDTO.extraComment,
            ingredient = Ingredient(componentDTO.ingredient.name),
            measurement = Measurement(
                quantity = componentDTO.measurement.quantity,
                unit = UnitModel( // Updated to MeasurementUnit
                    name = componentDTO.measurement.unit.name,
                    singular = componentDTO.measurement.unit.displaySingular,
                    plural = componentDTO.measurement.unit.displayPlural,
                    abbreviation = componentDTO.measurement.unit.abbreviation
                )
            ),
            position = componentDTO.position
        )
    }

    private fun mapToInstructionModel(instructionDTO: InstructionDTO): Instruction {
        return Instruction(
            id = instructionDTO.instructionID,
            text = instructionDTO.displayText,
            position = instructionDTO.position
        )
    }

    private fun mapToNutritionModel(nutritionDTO: NutritionDTO?): Nutrition {
        return Nutrition(
            calories = nutritionDTO?.calories ?: 0,
            protein = nutritionDTO?.protein ?: 0,
            fat = nutritionDTO?.fat ?: 0,
            carbohydrates = nutritionDTO?.carbohydrates ?: 0,
            sugar = nutritionDTO?.sugar ?: 0,
            fiber = nutritionDTO?.fiber ?: 0
        )
    }


    fun getRecipes(recipeDTOs: List<RecipeDTO>): List<Recipe> {
        return recipeDTOs.map { mapToRecipeModel(it) }
    }

}