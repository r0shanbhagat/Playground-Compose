package com.playground.movie.data.api

import com.playground.movie.data.dto.SearchResults
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*


/**
 * @Details :MovieServiceImpl
 * @Author Roshan Bhagat
 */
class MovieServiceImpl(private val client: HttpClient) : MovieService {

    override suspend fun getMovieData(parameters: Parameters): SearchResults {
        return client.get { url(MovieService.MOVIE_URL) }
    }

}