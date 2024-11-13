package com.tasty.recipesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.RecipeViewModel
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.dtos.RecipeDTO
import java.io.IOException
class RecipeActivity : AppCompatActivity() {


    fun loadRecipesFromJson(context: Context, fileName: String): List<RecipeDTO>? {
        return try {
            // Read JSON file from assets
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

            // Convert JSON string to List<RecipeDTO>
            val listType = object : TypeToken<List<RecipeDTO>>() {}.type
            Gson().fromJson(jsonString, listType)

        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        Log.d("RecipeData", "Start")

        // Load RecipeDTOs from JSON file
        val recipeDTOs = loadRecipesFromJson(this, "multiple_recipes.json")

        // Load recipes into ViewModel
        recipeDTOs?.let { viewModel.loadRecipes(it) }

        // Observe the recipes LiveData
        viewModel.recipes.observe(this, Observer { recipes ->
            // Update UI with the list of recipes
        })

        viewModel.recipes.observe(this, Observer { recipes ->
            if (recipes.isNullOrEmpty()) {
                Log.d("RecipeData", "No recipes found")
            }
        })
            viewModel.recipes.observe(this, Observer { recipes ->
            // Loop through each RecipeModel item and print properties to Logcat
            for (recipe in recipes) {
                Log.d("RecipeData", "Recipe ID: ${recipe.id}")
                Log.d("RecipeData", "Recipe Name: ${recipe.name}")
                Log.d("RecipeData", "Recipe Description: ${recipe.description}")
                Log.d("RecipeData", "Thumbnail URL: ${recipe.thumbnailUrl}")
                Log.d("RecipeData", "Country: ${recipe.country}")
                Log.d("RecipeData", "Number of Servings: ${recipe.numServings}")

                // Add more properties if needed
            }
        })
    }


}