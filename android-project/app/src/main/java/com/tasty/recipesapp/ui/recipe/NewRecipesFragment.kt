package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.RecipeModel
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

        // Add dynamic fields for ingredients and instructions

        // Save button
        binding.saveRecipeButton.setOnClickListener { saveRecipe() }
    }

    private fun addDynamicIngredientField() {
        val ingredientField = EditText(requireContext()).apply {
            hint = "Enter ingredient"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun addDynamicInstructionField() {
        val instructionField = EditText(requireContext()).apply {
            hint = "Enter instruction"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun saveRecipe() {
        val name = binding.recipeNameInput.text.toString()
        val description = binding.recipeDescriptionInput.text.toString()
        val pictureUrl = binding.pictureUrlInput.text.toString()
        val videoUrl = binding.videoUrlInput.text.toString()


        val recipe = RecipeModel(
            id = 0,
            name = name,
            description = description,
            thumbnailUrl = pictureUrl,
            originalVideoUrl = videoUrl,

        )

        val recipeEntity = RecipeEntity(json = Gson().toJson(recipe))

        val recipeDao = RecipeDatabase.getInstance(requireContext().applicationContext).recipeDao()

        lifecycleScope.launch {
            try {
                recipeDao.insertRecipe(recipeEntity)
                findNavController().navigateUp() // Navigate back on successful save
            } catch (e: Exception) {
                Log.e("NewRecipesFragment", "Error inserting recipe: ${e.message}", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewRecipesFragment()
    }
}
