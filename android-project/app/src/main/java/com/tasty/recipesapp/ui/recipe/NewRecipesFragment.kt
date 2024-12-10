package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.entities.RecipeDatabase
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewRecipesFragment : Fragment() {

    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listener for Save button
        binding.saveRecipeButton.setOnClickListener { saveRecipe() }
    }

    private fun saveRecipe() {
        val name = binding.recipeNameInput.text.toString().trim()
        val description = binding.recipeDescriptionInput.text.toString().trim()
        val pictureUrl = binding.pictureUrlInput.text.toString().trim()
        val videoUrl = binding.videoUrlInput.text.toString().trim()

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Name and description cannot be empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val recipe = RecipeModel(
            id = 0,
            name = name,
            description = description,
            thumbnailUrl = pictureUrl,
            originalVideoUrl = videoUrl,
        )

        val recipeJson = Gson().toJson(recipe)
        val recipeEntity = RecipeEntity(json = recipeJson)

        // Insert recipe into the database
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val db = RecipeDatabase.getDatabase(requireContext())
                db.recipeDao().insertRecipe(recipeEntity)

                // Switch back to the main thread to update UI
                launch(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Recipe saved successfully!", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp() // Navigate back
                }
            } catch (e: Exception) {
                Log.e("NewRecipesFragment", "Error inserting recipe: ${e.message}", e)
                launch(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to save recipe. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
