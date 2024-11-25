package com.tasty.recipesapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.tasty.recipesapp.entities.RecipeEntity

class RecipeDiffCallback : DiffUtil.ItemCallback<RecipeEntity>() {

    override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
        // Check if the items have the same ID (the unique identifier for your entity)
        return oldItem.internalId == newItem.internalId
    }

    override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
        // Check if the contents of the items are the same (you can extend this logic to compare other fields)
        return oldItem.json == newItem.json
    }
}
