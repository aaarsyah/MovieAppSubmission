package com.example.capstoneawalexpert.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneawalexpert.core.domain.usecase.MovieUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun searchMovies(query: String) = movieUseCase.searchMovies(query).asLiveData()

}