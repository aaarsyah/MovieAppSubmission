package com.example.capstoneawalexpert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneawalexpert.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}

