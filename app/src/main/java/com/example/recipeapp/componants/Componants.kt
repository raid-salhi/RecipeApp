package com.example.recipeapp.componants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.model.Meal
import com.example.recipeapp.navigation.BottomNavItem
import com.example.recipeapp.navigation.Screens
import com.example.recipeapp.ui.theme.Background
import com.example.recipeapp.ui.theme.BlackText

@Composable
fun RecipeItemCard(index: Int, item: Meal,navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(
                top = if (index.mod(2) != 0) 40.dp else 0.dp,
                bottom = 0.dp,
                start = 8.dp,
                end = 8.dp
            )
            .fillMaxWidth()
            .clickable {
                       navController.navigate(Screens.CookingScreen.name + "?meal={meal}".replace(
                           oldValue = "{meal}",
                           newValue = item.strMeal
                       ))
            },
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
fun MyTopBarCustom(navController: NavController, isVisible: Boolean, title: String) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically {
            it
        }
    ){
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
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
@Composable
fun MyBottomBar(navController: NavController) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(15.dp),
        elevation = 10.dp,
        color = Color.Transparent
    ) {
        BottomAppBar(
            backgroundColor = Color.White,
            contentColor = Color(0x731C0F13),
            cutoutShape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val items = listOf(
                BottomNavItem.Home,
                BottomNavItem.Latest,
                BottomNavItem.Cooking,
                BottomNavItem.Settings,
            )
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination=navBackStackEntry?.destination
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                items.forEach { item ->
                    BottomNavigationItemCustom(
                        selected = item.screen_route== currentDestination?.route?.split("?")?.get(0),
                        onClick = {
                            navController.navigate(item.screen_route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        item = item
                    )
                }
            }

        }
    }

}
@Composable
fun BottomNavigationItemCustom(selected: Boolean, onClick: () -> Unit, item: BottomNavItem) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) BlackText else Color.Transparent)
            .clickable { onClick() }
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(5.dp)
            .align(
                Alignment.Center
            )) {
            androidx.compose.material.Icon(painter = painterResource(id = item.icon), contentDescription = null , tint = if (selected) Color.White else Color(0x731C0F13))
            androidx.compose.material.Text(
                text = item.title,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nunito_light)),
                color =  if (selected) Color.White else Color(0x731C0F13),
            )
        }
    }
}


@Composable
fun RecommendationBox(navController: NavController) {
    androidx.compose.material3.Surface(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screens.CookingScreen.name + "?meal={meal}".replace(
                    oldValue = "{meal}",
                    newValue = "Vegan Lasagna"
                ))
            },
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder3),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Vegan Lasagna",
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
    androidx.compose.material3.Surface(
        modifier = Modifier
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
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
    androidx.compose.material3.Surface(
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
fun RecipeOfTheDayBox(navController: NavController) {
    androidx.compose.material3.Surface(
        modifier = Modifier
            .width(280.dp)
            .clickable {
                navController.navigate(Screens.CookingScreen.name + "?meal={meal}".replace(
                    oldValue = "{meal}",
                    newValue = "Bigos (Hunters Stew)"
                ))
            },
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            HeadingText(
                firstText = "Recipe",
                secondText = "of the day",
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "Bigos (Hunters Stew)",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.worksans_medium)),
                color = BlackText,
                modifier = Modifier.padding(top = 25.dp)
            )
            TimeRow(Modifier.padding(top = 5.dp, bottom = 15.dp))
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
            )
            ){
                append("$firstText \n \n")
            }
            withStyle(style = SpanStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.worksans_light)),
                color = BlackText
            )
            ){
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
@Composable
fun BrowseButton(navController: NavController) {
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
            color = BlackText,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.worksans_semibold))
        )
    }
}

