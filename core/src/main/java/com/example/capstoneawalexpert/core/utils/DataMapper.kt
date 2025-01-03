package com.example.capstoneawalexpert.core.utils
import com.example.capstoneawalexpert.core.data.source.local.entity.MovieEntity
import com.example.capstoneawalexpert.core.data.source.remote.response.ResultsItem
import com.example.capstoneawalexpert.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                overview = it.overview,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds.joinToString(","),
                posterPath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                overview = it.overview,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }



    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        overview = input.overview,
        originalTitle = input.originalTitle,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        genreIds = input.genreIds,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )

}