package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.api.MoviesApi
import com.example.tmdbapp.data.movieData.Movies
import com.example.tmdbapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    val api: MoviesApi
): MoviesRepository {
    override suspend fun getPopularMovies(): Movies {
        return api.getPopularMovies("69d66957eebff9666ea46bd464773cf0")
    }
}