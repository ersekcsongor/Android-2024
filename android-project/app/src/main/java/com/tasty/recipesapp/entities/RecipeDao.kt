package com.tasty.recipesapp.entities

import androidx.room.*

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity?

    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}