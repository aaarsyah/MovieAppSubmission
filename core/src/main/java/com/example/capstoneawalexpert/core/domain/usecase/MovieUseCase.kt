package com.example.capstoneawalexpert.core.domain.usecase

import com.example.capstoneawalexpert.core.data.Resource
import com.example.capstoneawalexpert.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun searchMovies(query: String): Flow<Resource<List<Movie>>>
}