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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipeapp.R
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

@Composable
fun RecommendationBox() {
    Surface(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Vegan Thai Curry Soup",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "Spice lovers will slurp up this soup in seconds. Featuring chili powder, smoked paprika, and cayenne pepper, every bowl brings the heat.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = BlackText,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 25.dp)
                    .fillMaxWidth()
            )
            TimeRow(modifier = Modifier.padding(top = 5.dp))

        }
    }
}

@Composable
fun CockLikeProBox() {
    Surface(
        modifier = Modifier
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ){
        Column(
            modifier= Modifier
                .padding(25.dp)) {
            HeadingText(
                firstText = "Cook",
                secondText = "like pro",
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "Thermomix advanced \n tips and tricks ",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                modifier = Modifier.padding(top = 25.dp)
            )
        }
    }
}

@Composable
fun CheckUpdatesBox(modifier: Modifier) {
    Surface(
        modifier = modifier
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            HeadingText(
                firstText = "Check",
                secondText = "New Updates",
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}

@Composable
fun RecipeOfTheDayBox() {
    Surface(
        modifier = Modifier
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier= Modifier
            .padding(25.dp)) {
            HeadingText(
                firstText ="Recipe",
                secondText="of the day",
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "Roasted Pumpkin Soup",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                modifier = Modifier.padding(top = 25.dp)
            )
            TimeRow(Modifier.padding(top=5.dp, bottom = 15.dp))
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun HeadingText(firstText: String, secondText: String, modifier: Modifier=Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                color = BlackText
            )){
                append("$firstText \n \n")
            }
            withStyle(style = SpanStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.worksans_light)),
                color = BlackText
            )){
                append(secondText)
            }
        },
        modifier=modifier
    )
}

@Composable
fun TimeRow(modifier:Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
            ){
        Icon(painter = painterResource(id =R.drawable.clock ), contentDescription = null, tint = BlackText)
        Text(
            text = "50 min",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_light)),
            color = Color(0x731C0F13),
            modifier= Modifier.padding(start=4.dp,end = 12.dp))
        Icon(painter = painterResource(id =R.drawable.tableware ), contentDescription = null, tint = BlackText)
        Text(
            text = "4 ppl",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_light)),
            color = Color(0x731C0F13),
            modifier= Modifier.padding(start=4.dp,end = 10.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(isVisible:Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically {
            it
        }
    ){
        TopAppBar(
            title = {
                Text(
                    text = "Cooksy",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                    color = BlackText
                )
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription ="search", tint = BlackText )
                }
            },
            modifier = Modifier.padding(20.dp),
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Background)
        )
    }

}
