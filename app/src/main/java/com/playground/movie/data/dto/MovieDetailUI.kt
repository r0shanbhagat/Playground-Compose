package com.playground.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailUI(
    var title: String,
    var body: String,
    var image: String,
    var year: String,
    var imdb: String

) 