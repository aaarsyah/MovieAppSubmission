package com.example.capstoneawalexpert.core.data.source.local

import com.example.capstoneawalexpert.core.data.source.local.room.MovieDao
import com.example.capstoneawalexpert.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun searchMovies(query: String): Flow<List<MovieEntity>> = movieDao.searchMovies("%$query%")
}