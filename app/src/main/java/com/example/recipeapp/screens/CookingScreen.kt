package com.example.recipeapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.componants.BrowseButton
import com.example.recipeapp.componants.HeadingText
import com.example.recipeapp.componants.MyTopBarCustom
import com.example.recipeapp.model.Ingredient
import com.example.recipeapp.model.Meal
import com.example.recipeapp.model.Recipes
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingScreen(navController: NavHostController, meal: String?,cookingScreenViewModel: CookingScreenViewModel= hiltViewModel()) {
    var item by remember {
        mutableStateOf(Recipes(emptyList()))
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    var isVisible1 by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = 3, block = {
        delay(200)
        isVisible=!isVisible
    })

    Scaffold (
        topBar = {
            MyTopBarCustom(navController = navController, isVisible = isVisible, title = "Cooking Time!" )
        },
        containerColor = Background
            ) {
        if (meal == "")
            RedirectionScreen(it,navController)
        else
        {
           LaunchedEffect(key1 = 6, block = {
               cookingScreenViewModel.getRecipeByName(meal!!).let { list ->
                   item=list
               }
           })
            if (item.meals.isEmpty())
                LinearProgressIndicator(modifier = Modifier.padding(it))
            else{
                LaunchedEffect(key1 = 8, block = {
                    delay(200)
                    isVisible1=!isVisible1
                })
                CookingContent(it = it, meal = item.meals.first(),isVisible = isVisible1)
            }

        }


    }
}

@Composable
fun CookingContent(it: PaddingValues, meal: Meal, isVisible: Boolean) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(it),
        color = Background
            ){
        Column(
            Modifier
                .padding(start = 25.dp, end = 25.dp, bottom = 15.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecipeDetailsCard(meal,isVisible)
            IngredientsBox(meal,isVisible)
        }
    }
}

@Composable
fun IngredientsBox(meal: Meal, isVisible: Boolean) {
    val listOfIngredients = filteredList(meal)
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            tween(delayMillis = 100)
        ) + slideInHorizontally(
            tween(delayMillis = 100)
        ){
            - it/2
        }){
        HeadingText(firstText = "Ingredients", secondText = "")
        Surface(
            modifier = Modifier
                .padding(top = 0.dp, bottom = 85.dp)
                .fillMaxWidth(),
            color = Background
        ){
            Column {
                listOfIngredients.forEach{ it ->
                    Box(modifier = Modifier.padding(5.dp)) {
                        IngredientRow(ingredient = it)
                    }

                }
            }
        }
    }

    
}

@Composable
fun IngredientRow(ingredient: Ingredient) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))
        .background(Color.White)
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .padding(10.dp)
                .background(Background)
                .size(70.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                )){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://www.themealdb.com/images/ingredients/${ingredient.title}-Small.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.placeholder1),
                )
            }
            Text(
                text = ingredient.title!!,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(
            text = ingredient.amount!!,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_light)),
            color =  Color(0x731C0F13),
            textAlign = TextAlign.Center,
        )
    }

}

fun filteredList(meal: Meal): ArrayList<Ingredient> {
    val filtredList = ArrayList<Ingredient>()
    if(meal.strIngredient1 != null){
        if (meal.strIngredient1.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient1, amount = meal.strMeasure1))
    }
    if(meal.strIngredient2 != null){
        if (meal.strIngredient2.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient2, amount = meal.strMeasure2))
    }

    if(meal.strIngredient3 != null){
        if (meal.strIngredient3.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient3, amount = meal.strMeasure3))
    }

    if(meal.strIngredient4 != null){
        if (meal.strIngredient4.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient4, amount = meal.strMeasure4))
    }

    if(meal.strIngredient5 != null){
        if (meal.strIngredient5.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient5, amount = meal.strMeasure5))
    }

    if(meal.strIngredient6 != null){
        if (meal.strIngredient6.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient6, amount = meal.strMeasure6))
    }

    if(meal.strIngredient7 != null){
        if (meal.strIngredient7.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient7, amount = meal.strMeasure7))
    }

    if(meal.strIngredient8 != null){
        if (meal.strIngredient8.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient8, amount = meal.strMeasure8))
    }

    if(meal.strIngredient9 != null){
        if (meal.strIngredient9.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient9, amount = meal.strMeasure9))
    }

    if(meal.strIngredient10 != null){
        if (meal.strIngredient10.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient10, amount = meal.strMeasure10))
    }

    if(meal.strIngredient11 != null){
        if (meal.strIngredient11.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient11, amount = meal.strMeasure11))
    }

    if(meal.strIngredient12 != null){
        if (meal.strIngredient12.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient12, amount = meal.strMeasure12))
    }

    if(meal.strIngredient13 != null){
        if (meal.strIngredient13.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient13, amount = meal.strMeasure13))
    }

    if(meal.strIngredient14 != null){
        if (meal.strIngredient14.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient14, amount = meal.strMeasure14))
    }

    if(meal.strIngredient15 != null){
        if (meal.strIngredient15.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient15, amount = meal.strMeasure15))
    }

    if(meal.strIngredient16 != null){
        if (meal.strIngredient16.isNotEmpty() )
            filtredList.add(Ingredient(title = meal.strIngredient16, amount = meal.strMeasure16))
    }

    if(meal.strIngredient17 != null){
        if (meal.strIngredient17.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient17, amount = meal.strMeasure17))
    }

    if(meal.strIngredient18 != null){
        if (meal.strIngredient18.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient18, amount = meal.strMeasure18))
    }

    if(meal.strIngredient19 != null){
        if (meal.strIngredient19.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient19, amount = meal.strMeasure19))
    }

       if(meal.strIngredient20 != null){
        if (meal.strIngredient20.isNotEmpty())
            filtredList.add(Ingredient(title = meal.strIngredient20, amount = meal.strMeasure20))
    }


    return filtredList
}

@Composable
fun RecipeDetailsCard(meal: Meal, isVisible: Boolean) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            tween(delayMillis = 100)
        ) + slideInHorizontally(
            tween(delayMillis = 100)
        ){
            - it/2
        }){

        Surface(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                Text(
                    text = meal.strMeal,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.worksans_medium)),
                    color = BlackText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxWidth()

                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.strMealThumb)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder1),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                )

                androidx.compose.material3.Text(
                    text = meal.strInstructions,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = BlackText,
                    maxLines = if (!isExpanded) 4 else 100,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 25.dp)
                        .fillMaxWidth()
                        .clickable {
                            isExpanded = !isExpanded
                        }
                )

            }
        }
    }

}

@Composable
fun RedirectionScreen(paddingValues: PaddingValues,navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Background)) {
        Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(25.dp)) {
            Image(painter = painterResource(id = R.drawable.cooking_bro), contentDescription = null )
            Text(
                text = "Discover our recipes collection , Choose one and let's start cooking !",
                fontSize = 26.sp,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                fontFamily = FontFamily(Font(R.font.worksans_semibold)),
                textAlign = TextAlign.Center,
                color = BlackText,
            )
            BrowseButton(navController = navController)
        }
    }
}
