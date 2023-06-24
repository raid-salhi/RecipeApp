package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.componants.MyTopBarCustom
import com.example.recipeapp.componants.RecipeItemCard
import com.example.recipeapp.model.Meal
import com.example.recipeapp.ui.theme.Background
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestScreen(navController: NavController,latestScreenViewModel: LatestScreenViewModel= hiltViewModel()){
    var isVisible by remember {
        mutableStateOf(false)
    }
    var isLoaded by remember {
        mutableStateOf(false)
    }
    val list= latestScreenViewModel.data
    LaunchedEffect(key1 = 3, block = {
        delay(200)
        isVisible=!isVisible
    })

    Scaffold(
        topBar = {
            MyTopBarCustom(navController,isVisible,title = "Latest Recipes")
        },
        containerColor = Background
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            color = Background) {
            if (list.value.meals.isEmpty())
                LinearProgressIndicator()
            else {
                LaunchedEffect(key1 = 3, block = {
                    delay(200)
                    isLoaded = !isLoaded
                })
                LatestContent(isLoaded, list.value.meals, navController = navController)
            }
        }
    }

}

@Composable
fun LatestContent(isVisible: Boolean, listRecipes: List<Meal>,navController: NavController) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically {
            it
        }
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp, bottom = 90.dp)
        ){
            itemsIndexed(
                listRecipes,
                key = { index,item ->
                    item.idMeal.hashCode()
                }
            ) { index, item ->

                RecipeItemCard(index,item, navController = navController)
            }
        }
    }



}


