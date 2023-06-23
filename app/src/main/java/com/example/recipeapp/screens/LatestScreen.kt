package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.model.Meal
import com.example.recipeapp.model.Recipes
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestScreen(navController: NavController,latestScreenViewModel: LatestScreenViewModel= hiltViewModel()){
    var isVisible by remember {
        mutableStateOf(false)
    }
    var isLoaded by remember {
        mutableStateOf(false)
    }
    val list= latestScreenViewModel.data
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
            if (list.value.meals.isEmpty())
                LinearProgressIndicator()
            else
                LaunchedEffect(key1 = 3, block = {
                    delay(200)
                    isLoaded=!isLoaded
                })
                LatestContent(isLoaded,list.value.meals)
        }
    }

}

@Composable
fun LatestContent(isVisible: Boolean, listRecipes: List<Meal>) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically {
            it
        }
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp, bottom = 25.dp)
        ){
            itemsIndexed(
                listRecipes,
                key = { index,item ->
                    item.idMeal.hashCode()
                }
            ) { index, item ->

                RecipeItemCard(index,item)
            }
        }
    }



}

@Composable
fun RecipeItemCard(index: Int, item: Meal) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(
                top = if (index.mod(2) != 0) 40.dp else 0.dp,
                bottom = 0.dp,
                start = 8.dp,
                end = 8.dp
            )
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.strMealThumb)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder1),
                modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
            )
            Text(
                text = item.strMeal,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                modifier = Modifier.padding(top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.strCategory +" / "+ item.strArea,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.nunito_light)),
                color = Color(0x731C0F13),
                modifier= Modifier.padding(top = 10.dp)
            )
            TimeRow(modifier = Modifier.padding(top = 50.dp))
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
