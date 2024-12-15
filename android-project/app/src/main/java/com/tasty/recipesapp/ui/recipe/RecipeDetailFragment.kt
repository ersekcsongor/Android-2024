package com.tasty.recipesapp.ui.recipe

import com.tasty.recipesapp.R
import RecipeApiClient
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.models.Recipe
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private val recipeApiClient = RecipeApiClient() // Initialize RecipeApiClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve recipeId from arguments
        val recipeId = arguments?.getInt("recipeId") ?: run {
            showError("Invalid Recipe ID")
            return
        }

        // Fetch and display recipe details
        fetchRecipeDetails(recipeId)

        // Set up the back button
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun fetchRecipeDetails(recipeId: Int) {
        lifecycleScope.launch {
            try {
                // Fetch the recipe using RecipeApiClient
                val recipe = recipeApiClient.getRecipeById(recipeId)

                if (recipe != null) {
                    bindRecipeData(recipe)
                } else {
                    showError("Recipe not found")
                }
            } catch (e: Exception) {
                Log.e("RecipeDetailsFragment", "Error fetching recipe", e)
                showError("Failed to fetch recipe")
            }
        }
    }

    private fun bindRecipeData(recipe: Recipe) {
        // Set Recipe Name and Description
        binding.recipeName.text = recipe.name
        binding.recipeDescription.text = recipe.description

        Glide.with(this)
            .load(recipe.thumbnailUrl)
            .into(binding.recipeThumbnail)

        // Display Video URL as a Video View or External Link
        if (!recipe.originalVideoUrl.isNullOrEmpty()) {
            binding.recipeVideoView.visibility = View.VISIBLE
            binding.recipeVideoView.setVideoPath(recipe.originalVideoUrl)
            binding.recipeVideoView.setOnPreparedListener { it.start() } // Autoplay on load
        } else {
            binding.recipeVideoView.visibility = View.GONE
        }

        // Display Keywords if Available
        if (!recipe.keywords.isNullOrEmpty()) {
            binding.recipeKeywords.visibility = View.VISIBLE
            binding.recipeKeywords.text = "Keywords: ${recipe.keywords}"
        }

        // Display Servings if Available
        if (recipe.numServings > 0) {
            binding.recipeServings.visibility = View.VISIBLE
            binding.recipeServings.text = "Servings: ${recipe.numServings}"
        }

        // Display Country if Available
        if (!recipe.country.isNullOrEmpty()) {
            binding.recipeCountry.visibility = View.VISIBLE
            binding.recipeCountry.text = "Country: ${recipe.country}"
        }
    }


    private fun showError(message: String) {
        binding.recipeName.text = "Error"
        binding.recipeDescription.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
