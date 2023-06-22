package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.navigation.Screens
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    var isVisible by remember {
        mutableStateOf(false)
    }
    var isLogoVisible by remember {
        mutableStateOf(false)
    }
    var isButtonVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = 1, block = {
        delay(100)
        isLogoVisible=!isLogoVisible
        delay(500)
        isButtonVisible=!isButtonVisible
        delay(900)
        isVisible=!isVisible
    })
   Column(
       verticalArrangement = Arrangement.SpaceBetween,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier
           .fillMaxSize()
           .background(
               Background
           )
           .padding(top = 50.dp, bottom = 50.dp, start = 30.dp, end = 30.dp)
   ) {
       AnimatedVisibility(
           visible = isLogoVisible,
           enter = fadeIn() + slideInVertically(
               animationSpec = tween(durationMillis = 400, easing = LinearEasing)
           ) {
              - it
           }
       ) {
           Text(
               text = "Cooksy",
               fontFamily = FontFamily(Font(R.font.worksans_semibold)),
               fontSize = 24.sp,
               color = BlackText
           )
       }
       AnimatedVisibility(
           visible = isVisible,
           enter = fadeIn() + slideInVertically(
               animationSpec = tween(durationMillis = 500, easing = LinearEasing)
           ) {
               it/4
           }
       ) {
           Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Image(
                   painter = painterResource(id = R.drawable.illustration),
                   contentDescription = null,
                   modifier = Modifier.size(200.dp)
               )
               Text(
                   text = "All the recipes \n" +
                           "on your fingertips",
                   fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                   fontSize = 30.sp,
                   color = BlackText,
                   textAlign = TextAlign.Center,
                   modifier = Modifier.padding(top = 50.dp)
               )
               Text(
                   text = "Cooking on Thermomix made easy",
                   fontFamily = FontFamily(Font(R.font.nunito_light)),
                   fontSize = 18.sp,
                   color = Color(0xE51C0F13),
               )
           }
       }
       AnimatedVisibility(
           visible = isButtonVisible,
           enter = fadeIn() + slideInVertically(
               animationSpec = tween(durationMillis = 500, easing = LinearEasing)
           ){
               it/2
           }
       ){
           Button(
               onClick = { navController.navigate(Screens.HomeScreen.name) },
               shape = RoundedCornerShape(8.dp),
               colors = ButtonDefaults.buttonColors(backgroundColor = BlackText),
               modifier = Modifier
                   .fillMaxWidth()
                   .height(50.dp)
           ) {
               Text(
                   text = "Let's start",
                   color= Color.White,
                   fontSize = 18.sp,
                   fontFamily = FontFamily(Font(R.font.worksans_semibold))
               )
           }

       }
       }

   }
