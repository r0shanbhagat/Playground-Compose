package com.playground.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    val response: String? = "",
    @SerialName("Search")
    val searches: List<Search>? = listOf(),
    val totalResults: String? = ""
)