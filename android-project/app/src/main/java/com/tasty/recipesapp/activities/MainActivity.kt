package com.tasty.recipesapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.RecipeViewModel
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.dtos.RecipeDTO
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("test123","asdsad")
        // Find the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the BottomNavigationView with NavController
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        Log.d("RecipeData", "MainActivity started")

        // Load RecipeDTOs from JSON file
        val recipeDTOs = loadRecipesFromJson(this, "multiple_recipes.json")

        // Log the result of loading recipes
        if (recipeDTOs == null) {
            Log.d("RecipeData", "Failed to load recipes from JSON file")
        } else {
            Log.d("RecipeData", "Recipes loaded successfully from JSON file")
            viewModel.loadRecipes(recipeDTOs)
        }

        // Observe the recipes LiveData with a single observer
        viewModel.recipes.observe(this, Observer { recipes ->
            if (recipes.isNullOrEmpty()) {
                Log.d("RecipeData", "No recipes found")
            } else {
                // Log each recipe's details
                for (recipe in recipes) {
                    Log.d("RecipeData", "Recipe ID: ${recipe.id}")
                    Log.d("RecipeData", "Recipe Name: ${recipe.name}")
                    Log.d("RecipeData", "Recipe Description: ${recipe.description}")
                    Log.d("RecipeData", "Thumbnail URL: ${recipe.thumbnailUrl}")
                    Log.d("RecipeData", "Country: ${recipe.country}")
                    Log.d("RecipeData", "Number of Servings: ${recipe.numServings}")
                }
            }
        })
    }

    // Method to load recipes from a JSON file in the assets folder
    private fun loadRecipesFromJson(context: Context, fileName: String): List<RecipeDTO>? {
        return try {
            // Read the JSON file from the assets folder
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

            // Convert the JSON string to a List<RecipeDTO>
            val listType = object : TypeToken<List<RecipeDTO>>() {}.type
            Gson().fromJson(jsonString, listType)
        } catch (e: IOException) {
            Log.e("RecipeData", "Error reading JSON file", e)
            null
        }
    }
}