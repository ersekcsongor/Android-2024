package com.tasty.recipesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.ItemRecipeBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.models.RecipeModel

class RecipeAdapter2(
    recipes: List<RecipeEntity>,
    private val onDelete: (RecipeEntity) -> Unit,
    private val onDetails: (Long) -> Unit // Updated to pass recipe ID for navigation
) : RecyclerView.Adapter<RecipeAdapter2.RecipeViewHolder>() {

    private val recipeList = recipes.toMutableList()

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            try {
                val recipeData = Gson().fromJson(recipe.json, RecipeModel::class.java)
                binding.recipeName.text = recipeData.name
                binding.recipeDescription.text = recipeData.description
            } catch (e: Exception) {
                binding.recipeName.text = "Invalid Recipe"
                binding.recipeDescription.text = "Error parsing recipe details."
            }

            // Handle delete button click
            binding.deleteButton.setOnClickListener {
                onDelete(recipe)
            }

            // Handle details button click
            binding.detailsButton.setOnClickListener {
                onDetails(recipe.internalId) // Pass recipe ID to callback
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount() = recipeList.size

    fun deleteRecipe(recipe: RecipeEntity) {
        val index = recipeList.indexOf(recipe)
        if (index != -1) {
            recipeList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
