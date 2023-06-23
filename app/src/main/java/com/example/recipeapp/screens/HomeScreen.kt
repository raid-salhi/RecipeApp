package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.componants.CheckUpdatesBox
import com.example.recipeapp.componants.CockLikeProBox
import com.example.recipeapp.componants.HeadingText
import com.example.recipeapp.componants.MyTopBar
import com.example.recipeapp.componants.RecipeOfTheDayBox
import com.example.recipeapp.componants.RecommendationBox
import com.example.recipeapp.navigation.Screens
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = 2, block = {
        delay(200)
        isVisible=!isVisible
    })
    Scaffold(
        topBar = {
                 MyTopBar(isVisible)
        },
        containerColor = Background
    ){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(it), color = Background) {
            MainContent(isVisible,navController)
        }
    }
}

@Composable
fun MainContent(isVisible: Boolean, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 80.dp, top = 25.dp, end = 25.dp, start = 25.dp)
        .verticalScroll(rememberScrollState())) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + slideInHorizontally{
                - it/2
            }){
            Row(modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())) {
                RecipeOfTheDayBox()
                Column(modifier = Modifier.padding(start = 15.dp, top = 30.dp)) {
                    CockLikeProBox()
                    CheckUpdatesBox(Modifier.padding(top=15.dp))
                }
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(delayMillis = 200)
            ) ){
            Column(modifier = Modifier.fillMaxSize()) {
                HeadingText(firstText = "René Redzepi", secondText ="recommends you" ,Modifier.padding(top = 50.dp))
                RecommendationBox()
                Button(
                    onClick = {
                              navController.navigate(Screens.LatestScreen.name)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Background),
                    border = BorderStroke(width = 1.5.dp, BlackText),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(bottom = 25.dp)
                ) {
                    Text(
                        text = "Browse more recipes",
                        color= BlackText,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.worksans_semibold))
                    )
                }
            }

        }
    }
}

