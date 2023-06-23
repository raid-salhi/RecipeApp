package com.example.recipeapp.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.componants.MyBottomBar
import com.example.recipeapp.screens.CookingScreen
import com.example.recipeapp.screens.HomeScreen
import com.example.recipeapp.screens.LatestScreen
import com.example.recipeapp.screens.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    var isVisible by remember {
        mutableStateOf(false)
    }
    Scaffold(bottomBar = {
        if (navController.currentBackStackEntryAsState().value?.destination?.route !=Screens.SplashScreen.name){
            LaunchedEffect(key1 = 5, block = {
                delay(100)
                isVisible=!isVisible
            })
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(300)) ){
                MyBottomBar(navController)
            }
        }

    }) {
        Surface(color = Color.Transparent) {
            val modifier = Modifier.padding(it)
            NavHost(navController = navController, startDestination = Screens.SplashScreen.name){
                composable(route = Screens.SplashScreen.name){
                    SplashScreen(navController)
                }
                composable(route = Screens.HomeScreen.name){
                    HomeScreen(navController)
                }
                composable(route = Screens.LatestScreen.name){
                    LatestScreen(navController)
                }
                composable(
                    route = Screens.CookingScreen.name+"?meal={meal}",
                    arguments = listOf(navArgument("meal"){
                        type= NavType.StringType
                        defaultValue = ""
                    })
                ){
                    it.arguments?.getString("meal").let { meal ->
                        CookingScreen(navController,meal)
                    }

                }
            }
        }
        }

}

