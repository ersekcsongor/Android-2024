package com.tasty.recipesapp.ui.recipe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.entities.RecipeDatabase
import com.tasty.recipesapp.models.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailsFragment2 : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

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
        val recipeId = arguments?.getLong("recipeId") ?: run {
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

    private fun fetchRecipeDetails(recipeId: Long) {
        val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()

        lifecycleScope.launch {
            val recipeEntity = withContext(Dispatchers.IO) {
                recipeDao.getRecipeById(recipeId)
            }

            if (recipeEntity != null) {
                try {
                    // Parse the recipe JSON
                    val recipeData = Gson().fromJson(recipeEntity.json, RecipeModel::class.java)
                    bindRecipeData(recipeData)
                } catch (e: Exception) {
                    showError("Failed to parse recipe data")
                }
            } else {
                showError("Recipe not found")
            }
        }
    }

    private fun bindRecipeData(recipeData: RecipeModel) {
        binding.recipeName.text = recipeData.name
        binding.recipeDescription.text = recipeData.description

        Glide.with(this)
            .load(recipeData.thumbnailUrl)
            .into(binding.recipeThumbnail)

        if (!recipeData.originalVideoUrl.isNullOrEmpty()) {
            binding.recipeVideoView.visibility = View.VISIBLE

            // Make the video URL clickable
            binding.recipeVideoView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(recipeData.originalVideoUrl)
                }
                startActivity(intent)
            }
        } else {
            binding.recipeVideoView.visibility = View.GONE
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
