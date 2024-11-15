package com.tasty.recipesapp.ui.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ItemRecipeBinding
import com.tasty.recipesapp.models.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit,
    private val onDetailsClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClick, onDetailsClick)
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            recipe: Recipe,
            onItemClick: (Recipe) -> Unit,
            onDetailsClick: (Recipe) -> Unit
        ) {
            binding.recipeName.text = recipe.name
            binding.recipeDescription.text = recipe.description

            // Use Glide to load the thumbnail URL into the ImageView
            Glide.with(binding.root.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.ic_profile) // Fallback image
                .into(binding.recipeImage)

            // Click listeners
            binding.root.setOnClickListener { onItemClick(recipe) }
            binding.detailsButton.setOnClickListener { onDetailsClick(recipe) }
        }
    }

}
