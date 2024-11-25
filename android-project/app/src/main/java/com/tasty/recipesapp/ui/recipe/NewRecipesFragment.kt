package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.tasty.recipesapp.R
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.dtos.ComponentDTO
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.Component
import com.tasty.recipesapp.models.Instruction
import com.tasty.recipesapp.models.Recipe

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NewRecipesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


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
            binding.addIngredientButton.setOnClickListener {
                addDynamicIngredientField()
            }

            binding.addInstructionButton.setOnClickListener {
                addDynamicInstructionField()
            }

            // Save button
            binding.saveRecipeButton.setOnClickListener {
                saveRecipe()
            }
        }

    private fun addDynamicIngredientField() {
        val ingredientField = EditText(requireContext()).apply {
            hint = "Enter ingredient"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        binding.ingredientsContainer.addView(ingredientField)
    }

    private fun addDynamicInstructionField() {
        val instructionField = EditText(requireContext()).apply {
            hint = "Enter instruction"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        binding.instructionsContainer.addView(instructionField)
    }

    private fun saveRecipe() {
        val name = binding.recipeNameInput.text.toString()
        val description = binding.recipeDescriptionInput.text.toString()
        val pictureUrl = binding.pictureUrlInput.text.toString()
        val videoUrl = binding.videoUrlInput.text.toString()

        // Collect all ingredient inputs
        val ingredients = mutableListOf<String>()
        for (i in 0 until binding.ingredientsContainer.childCount) {
            val ingredientField = binding.ingredientsContainer.getChildAt(i) as EditText
            val ingredient = ingredientField.text.toString()
            if (ingredient.isNotEmpty()) {
                ingredients.add(ingredient)
            }
        }

        // Collect all instruction inputs
        val instructions = mutableListOf<String>()
        for (i in 0 until binding.instructionsContainer.childCount) {
            val instructionField = binding.instructionsContainer.getChildAt(i) as EditText
            val instructionText = instructionField.text.toString()
            if (instructionText.isNotEmpty()) {
                instructions.add(instructionText)
            }
        }

        val recipe = Recipe(
            id = 0,
            name = name,
            description = description,
            thumbnailUrl = pictureUrl,
            keywords = listOf(), // Add keywords if required
            isPublic = true,
            userEmail = "user@example.com",
            originalVideoUrl = videoUrl,
            country = "",
            numServings = 0,
            components = ingredients.map { it }, // Use as components if applicable
            instructions = instructions,
            nutrition = null
        )

        // Convert Recipe to JSON and save (if applicable)
        val recipeEntity = RecipeEntity(json = Gson().toJson(recipe))
        // Save to database or API...

        // Navigate back
        findNavController().navigateUp()
    }


        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewRecipesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

















