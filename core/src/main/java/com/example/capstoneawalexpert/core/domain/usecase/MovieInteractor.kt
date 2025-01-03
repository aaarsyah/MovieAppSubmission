package com.example.capstoneawalexpert.core.domain.usecase

import com.example.capstoneawalexpert.core.domain.model.Movie
import com.example.capstoneawalexpert.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import com.example.capstoneawalexpert.core.data.Resource

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = movieRepository.searchMovies(query)
}