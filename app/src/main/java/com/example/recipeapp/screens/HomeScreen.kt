package com.example.recipeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
                 MyTopBar()
        },
        containerColor = Background
    ){
        Surface(modifier = Modifier.fillMaxWidth().padding(it), color = Background) {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)
        .verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier.fillMaxWidth()) {
            RecipeOfTheDayBox()
        }
    }
}

@Composable
fun RecipeOfTheDayBox() {
    Surface(
        modifier = Modifier
            .width(330.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier= Modifier
            .padding(25.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                        color = BlackText
                    )){
                        append("Recipe \n \n")
                    }
                    withStyle(style = SpanStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.worksans_light)),
                        color = BlackText
                    )){
                        append("of the day")
                    }
                })
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
fun MyTopBar() {
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
