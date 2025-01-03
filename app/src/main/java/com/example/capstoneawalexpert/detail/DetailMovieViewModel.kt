package com.example.capstoneawalexpert.detail

import androidx.lifecycle.ViewModel
import com.example.capstoneawalexpert.core.domain.model.Movie
import com.example.capstoneawalexpert.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}

