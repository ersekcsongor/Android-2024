package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.ui.recipe.RecipeAdapter
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val recipeViewModel: RecipeListViewModel by viewModels {
        RecipeListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        binding.profileRecipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the recipe list from the ViewModel
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            if (recipes.isNotEmpty()) {
                // Randomly select 3 recipes to display
                val randomRecipes = recipes.shuffled().take(3)

                // Set up the RecipeAdapter with the random recipes
                val recipeAdapter = RecipeAdapter(
                    randomRecipes,
                    onItemClick = { recipe -> navigateToRecipeDetail(recipe) },
                    onDetailsClick = { recipe -> navigateToRecipeDetail(recipe) }
                )
                binding.profileRecipeRecyclerView.adapter = recipeAdapter
            }
        }

        // Fetch recipes
        recipeViewModel.fetchRecipeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToRecipeDetail(recipe: Recipe) {
        // Navigation logic to the recipe detail screen
    }
}
