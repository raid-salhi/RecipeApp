package com.example.recipeapp.navigation

import com.example.recipeapp.R

const val meal = "meal"
sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Inspirations", R.drawable.idea,Screens.HomeScreen.name)
    object Latest: BottomNavItem("Recipes",R.drawable.receipes,Screens.LatestScreen.name)
    object Cooking: BottomNavItem("Cook Now",R.drawable.cook,Screens.CookingScreen.name)
    object Settings: BottomNavItem("Settings",R.drawable.settings, "")
}