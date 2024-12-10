package com.tasty.recipesapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L, // Auto-generated ID for the recipe
    @SerializedName("json_data")
    val json: String // JSON format to store recipe details
)