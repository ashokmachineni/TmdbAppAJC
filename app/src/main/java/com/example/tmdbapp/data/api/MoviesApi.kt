package com.example.tmdbapp.data.api

import com.example.tmdbapp.data.movieData.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("popular?")
    suspend fun getPopularMovies(@Query("api_key") api_key : String):Movies
}