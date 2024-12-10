package com.tasty.recipesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.entities.RecipeEntity
import com.google.gson.Gson
import com.tasty.recipesapp.models.RecipeModel

class RecipeAdapter2(
    private val recipes: List<RecipeEntity> // Pass the list of recipes to the adapter
) : RecyclerView.Adapter<RecipeAdapter2.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.recipeName)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.recipeDescription)
        private val detailsButton: Button = itemView.findViewById(R.id.detailsButton)
        private val wishlistButton: Button = itemView.findViewById(R.id.wishlistButton)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)

        fun bind(recipeEntity: RecipeEntity) {
            val recipeModel = Gson().fromJson(recipeEntity.json, RecipeModel::class.java)
            nameTextView.text = recipeModel.name
            descriptionTextView.text = recipeModel.description

            // Optional: Handle button clicks
            detailsButton.setOnClickListener {
                // Handle details button action
            }
            wishlistButton.setOnClickListener {
                // Handle wishlist button action
            }

            // Optional: Load an image into the ImageView (if applicable)
            // Glide.with(itemView.context).load(recipeModel.imageUrl).into(recipeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeEntity = recipes[position]
        holder.bind(recipeEntity)
    }

    override fun getItemCount(): Int = recipes.size
}
