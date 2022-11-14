package com.example.tmdbapp.presenter

import com.example.tmdbapp.data.movieData.Result

data class MoviesState(
    val isLoading: Boolean = false,
    val isError: String = "",
    val isSuccess: List<Result> = emptyList()
)
