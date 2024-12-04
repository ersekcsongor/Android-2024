package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.viewmodel.ProfileViewModel
import com.tasty.recipesapp.repository.RecipeRepository2
import com.tasty.recipesapp.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(
            RecipeRepository2(
                RecipeDatabase.getInstance(requireContext()).recipeDao()
            )
        )
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

        // Setup RecyclerView for displaying recipes
        val adapter = RecipeAdapter2() // You will need to create this adapter class
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        // Observe the list of recipes

        // Handle add recipe button click
        binding.addRecipeButton.setOnClickListener {
            navigateToNewRecipe()
        }
    }

    private fun navigateToNewRecipe() {
        findNavController().navigate(R.id.newRecipesFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}