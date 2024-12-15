package com.tasty.recipesapp.service

import com.tasty.recipesapp.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET("api/recipes")
    suspend fun getRecipes(): List<Recipe>
    @GET("api/recipes/{id}/")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe
}
