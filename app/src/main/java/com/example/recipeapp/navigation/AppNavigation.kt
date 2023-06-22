package com.example.recipeapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.screens.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ){
        composable(route = Screens.SplashScreen.name){
            SplashScreen(navController)
        }
    }
}