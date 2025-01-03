package com.example.capstoneawalexpert.core.data.source.remote

import android.util.Log
import com.example.capstoneawalexpert.core.data.source.remote.network.ApiResponse
import com.example.capstoneawalexpert.core.data.source.remote.network.ApiService
import com.example.capstoneawalexpert.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results.takeIf { it.isNotEmpty() } ?: emptyList()
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovies(query: String): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            val response = apiService.searchMovies(query)
            val dataArray = response.results.takeIf { it.isNotEmpty() } ?: emptyList()
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(dataArray))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
            .flowOn(Dispatchers.IO)
    }
}

