package com.example.tmdbapp.domain.repository

import com.example.tmdbapp.data.movieData.Movies

interface MoviesRepository {
    suspend fun getPopularMovies():Movies
}