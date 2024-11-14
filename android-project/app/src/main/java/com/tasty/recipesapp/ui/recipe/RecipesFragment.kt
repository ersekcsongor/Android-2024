package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tasty.recipesapp.R
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModelFactory

class RecipesFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels {
        RecipeListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        // Fetch data when the view is created
        viewModel.fetchRecipeData()

        // Observe the LiveData and log the recipe details
        viewModel.recipeList.observe(viewLifecycleOwner, Observer { recipes ->
            recipes.forEach { recipe ->
                Log.d("RecipeData", "Recipe ID: ${recipe.id}")
                Log.d("RecipeData", "Recipe Name: ${recipe.name}")
                Log.d("RecipeData", "Recipe Description: ${recipe.description}")
            }
        })

        return view
    }
}
