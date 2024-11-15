package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModelFactory

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipeViewModel: RecipeListViewModel by viewModels {
        RecipeListViewModelFactory(requireContext())
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

        // Set up RecyclerView
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe recipe list from ViewModel
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            val recipeAdapter = RecipeAdapter(
                recipes,
                onItemClick = { recipe -> navigateToRecipeDetail(recipe) },
                onDetailsClick = { recipe -> navigateToRecipeDetail(recipe) }
            )
            binding.recipeRecyclerView.adapter = recipeAdapter
        }

        // Fetch data
        recipeViewModel.fetchRecipeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToRecipeDetail(recipe: Recipe) {
        // Navigation logic
    }
}
