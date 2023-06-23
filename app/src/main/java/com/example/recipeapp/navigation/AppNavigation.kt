package com.example.recipeapp.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.componants.MyBottomBar
import com.example.recipeapp.screens.HomeScreen
import com.example.recipeapp.screens.LatestScreen
import com.example.recipeapp.screens.SplashScreen
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
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
            }
        }
        }

}

