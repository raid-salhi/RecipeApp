package com.example.recipeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipeapp.componants.MyTopBarCustom
import com.example.recipeapp.ui.theme.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingScreen(navController: NavHostController, meal: String?) {
    Scaffold (
        topBar = {
            MyTopBarCustom(navController = navController, isVisible = true, title = "Cooking Time!" )
        },
        containerColor = Background
            ) {
            RedirectionScreen(it)

    }
}

@Composable
fun CookingContent(it: PaddingValues) {

}

@Composable
fun RedirectionScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Background)) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
        ) {

        }
    }
}
