package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.commons.Resource
import com.example.tmdbapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke():Flow<Resource<List<com.example.tmdbapp.data.movieData.Result>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = moviesRepository.getPopularMovies().results
            emit(Resource.Success(movies))

        }catch (e: IOException){
            emit(Resource.Error("something wrong"))
        }catch (e: HttpException){
            emit(Resource.Error("something wrong"))
        }
    }
}