package com.example.capstoneawalexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)
