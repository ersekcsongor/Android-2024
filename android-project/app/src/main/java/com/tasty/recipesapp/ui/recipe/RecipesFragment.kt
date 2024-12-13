package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModelFactory

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipeViewModel: RecipeListViewModel by viewModels {
        RecipeListViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe recipes
        recipeViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            binding.recipeRecyclerView.adapter = RecipeAdapter(
                recipes,
                onItemClick = { recipe -> navigateToRecipeDetail(recipe) },
                onDetailsClick = { recipe -> navigateToRecipeDetail(recipe) }
            )
        }

        // Observe raw JSON for debugging
        recipeViewModel.rawJson.observe(viewLifecycleOwner) { json ->
            Log.d("RecipesFragment", "Raw JSON: $json")
        }

        // Fetch recipes
        recipeViewModel.getAllRecipesFromApi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToRecipeDetail(recipe: Recipe) {
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf("recipeId" to recipe.recipeID)
        )
    }
}
