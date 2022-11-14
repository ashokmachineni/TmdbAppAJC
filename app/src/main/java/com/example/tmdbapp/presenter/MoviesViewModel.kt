package com.example.tmdbapp.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.commons.Resource
import com.example.tmdbapp.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: MoviesUseCase
) : ViewModel(){
    private val _movieState = mutableStateOf(MoviesState())
    val movieState: State<MoviesState> = _movieState

    init {
        getMovies()
    }

    fun getMovies() {
        useCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _movieState.value = MoviesState(isLoading = true)
                }
                is Resource.Error -> {
                    _movieState.value = MoviesState(isError = "ohh")
                }
                is Resource.Success -> {
                    val mov = result.data
                    _movieState.value = MoviesState(isSuccess = mov ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}