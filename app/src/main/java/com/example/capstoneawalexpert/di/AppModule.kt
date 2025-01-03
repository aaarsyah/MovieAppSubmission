package com.example.capstoneawalexpert.di

import com.example.capstoneawalexpert.core.domain.usecase.MovieInteractor
import com.example.capstoneawalexpert.core.domain.usecase.MovieUseCase
import com.example.capstoneawalexpert.favorite.FavoriteViewModel
import com.example.capstoneawalexpert.home.HomeViewModel
import com.example.capstoneawalexpert.detail.DetailMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}