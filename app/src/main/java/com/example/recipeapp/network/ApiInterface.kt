package com.example.recipeapp.network

import com.example.recipeapp.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("search.php")
    suspend fun getAllRecipesByLetter(
        @Query("f") letter : String
    ) : Recipes
}