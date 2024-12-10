package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapters.RecipeAdapter2
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.entities.RecipeDatabase
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
        lifecycleScope.launch {
            val recipes = recipeDao.getAllRecipes() // Fetch recipes as an immutable list

            recipeAdapter = RecipeAdapter2(recipes) { recipe ->
                lifecycleScope.launch {
                    recipeDao.deleteRecipe(recipe) // Delete from the database
                    recipeAdapter.deleteRecipe(recipe) // Update the adapter
                }
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recipeAdapter
            }
        }


        binding.addRecipeButton.setOnClickListener {
            findNavController().navigate(R.id.newRecipesFragment)
        }
    }


    private fun updateRecyclerView() {
        lifecycleScope.launch {
            val updatedRecipes = RecipeDatabase.getDatabase(requireContext()).recipeDao().getAllRecipes()
            recipeAdapter = RecipeAdapter2(updatedRecipes) { recipeToDelete ->
                lifecycleScope.launch {
                    RecipeDatabase.getDatabase(requireContext()).recipeDao().deleteRecipe(recipeToDelete)
                    updateRecyclerView()
                }
            }
            binding.recyclerView.adapter = recipeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
