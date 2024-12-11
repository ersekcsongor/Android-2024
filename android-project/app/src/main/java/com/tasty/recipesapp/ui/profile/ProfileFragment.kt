package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapters.RecipeAdapter2
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.entities.RecipeDatabase
import com.tasty.recipesapp.entities.RecipeEntity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipeAdapter: RecipeAdapter2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()

        // Fetch recipes and set up the adapter
        lifecycleScope.launch {
            val recipes = recipeDao.getAllRecipes()

            // Initialize the adapter with delete and details handling
            recipeAdapter = RecipeAdapter2(
                recipes = recipes,
                onDelete = { recipe ->
                    lifecycleScope.launch {
                        recipeDao.deleteRecipe(recipe) // Delete from database
                        recipeAdapter.deleteRecipe(recipe) // Update UI
                    }
                },
                onDetails = { recipeId ->
                    navigateToRecipeDetail(recipeId)
                }
            )

            // Set up RecyclerView
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recipeAdapter
            }
        }

        // Navigate to Add Recipe screen
        binding.addRecipeButton.setOnClickListener {
            findNavController().navigate(R.id.newRecipesFragment)
        }
    }

    private fun navigateToRecipeDetail(recipeId: Long) {
        val bundle = bundleOf("recipeId" to recipeId.toInt())
        findNavController().navigate(R.id.recipeDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
