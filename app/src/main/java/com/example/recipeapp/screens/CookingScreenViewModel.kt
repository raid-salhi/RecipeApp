package com.example.recipeapp.screens

import androidx.lifecycle.ViewModel
import com.example.recipeapp.model.Meal
import com.example.recipeapp.repo.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookingScreenViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {
    suspend fun getRecipeByName(meal: String) = repository.getRecipeByName(meal)
}