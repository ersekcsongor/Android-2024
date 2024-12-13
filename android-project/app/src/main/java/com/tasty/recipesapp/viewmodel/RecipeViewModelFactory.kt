package com.tasty.recipesapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class RecipeListViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            return RecipeListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
