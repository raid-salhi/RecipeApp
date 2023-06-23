package com.example.recipeapp.repo

import com.example.recipeapp.model.Recipes
import com.example.recipeapp.network.ApiInterface
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getAllRecipesByLetter(letter : String):Recipes = apiInterface.getAllRecipesByLetter(letter)
}