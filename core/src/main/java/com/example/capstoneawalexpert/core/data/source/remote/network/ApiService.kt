package com.example.capstoneawalexpert.core.data.source.remote.network

import com.example.capstoneawalexpert.core.data.source.remote.response.ListMovieResponse
import com.example.capstoneawalexpert.core.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getList(): ListMovieResponse

    @GET("search/collection")
    suspend fun searchMovies(@Query("query") query: String): SearchResponse
}
