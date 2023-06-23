package com.example.recipeapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.Recipes
import com.example.recipeapp.repo.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestScreenViewModel @Inject constructor(repository: RecipesRepository) : ViewModel() {
    val data = mutableStateOf(Recipes(emptyList()))
    init {
        viewModelScope.launch {
            repository.getAllRecipesByLetter(letter = "b").let { it ->
                data.value=it
            }
        }
    }
}