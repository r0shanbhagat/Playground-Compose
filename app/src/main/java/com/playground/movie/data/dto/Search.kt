package com.playground.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Details MovieModel
 * @Author Roshan Bhagat
 */

@Serializable
data class Search(
    @SerialName("Poster")
    val poster: String,
    @SerialName("Title")
    val title: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Year")
    val year: String,
    @SerialName("imdbID")
    val imdb: String
)
