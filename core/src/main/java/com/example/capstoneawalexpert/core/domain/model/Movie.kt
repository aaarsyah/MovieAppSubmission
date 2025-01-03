package com.example.capstoneawalexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: String,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable