package com.playground.movie.data.dto

import com.playground.movie.core.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultResponse(
    @SerialName("Response")
    val response: String?,
    @SerialName("Search")
    val searches: List<Search>? = listOf(),
    @SerialName("totalResults")
    val totalResults: String?
) : BaseResponse()