package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestScreen(navController: NavController){
    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = 3, block = {
        delay(200)
        isVisible=!isVisible
    })

    Scaffold(
        topBar = {
            MyTopBarLatest(navController,isVisible)
        },
        containerColor = Background
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            color = Background) {
            LatestContent(isVisible)
        }
    }

}

@Composable
fun LatestContent(isVisible: Boolean) {
    val list = listOf(1.5,2.5,3.5,4.5)
    var index by remember {
        mutableStateOf(0)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ){
        itemsIndexed(
            list,
            key = { it,ti ->
                ti.hashCode()
            }
        ) { it, ti ->
            if (it ==1)
                index=it
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Gray),
                modifier = Modifier
                    .padding(top = if(it.mod(2)!=0) 30.dp else 10.dp , bottom = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            ) {
                Text(
                    text = ti.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBarLatest(navController: NavController,isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically {
            it
        }
    ){
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Latest Recipes",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                    color = BlackText
            ) },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = BlackText
                    )
                }
            },
            modifier = Modifier.padding(20.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Background)
        )
    }
}
