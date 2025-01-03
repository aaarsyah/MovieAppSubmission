package com.example.capstoneawalexpert.core.data

import com.example.capstoneawalexpert.core.data.source.local.LocalDataSource
import com.example.capstoneawalexpert.core.data.source.remote.RemoteDataSource
import com.example.capstoneawalexpert.core.data.source.remote.network.ApiResponse
import com.example.capstoneawalexpert.core.data.source.remote.response.ResultsItem
import com.example.capstoneawalexpert.core.domain.model.Movie
import com.example.capstoneawalexpert.core.domain.repository.IMovieRepository
import com.example.capstoneawalexpert.core.utils.AppExecutors
import com.example.capstoneawalexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.searchMovies(query).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.searchMovies(query)

            override suspend fun saveCallResult(data: List<ResultsItem>) {

            }
        }.asFlow()
    }
}

