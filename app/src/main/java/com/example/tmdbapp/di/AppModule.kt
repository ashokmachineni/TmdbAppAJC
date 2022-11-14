package com.example.tmdbapp.di

import com.example.tmdbapp.data.api.MoviesApi
import com.example.tmdbapp.data.repository.MoviesRepositoryImpl
import com.example.tmdbapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getMoviesServiceProvider(): MoviesApi{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository{
        return MoviesRepositoryImpl(api)
    }
}