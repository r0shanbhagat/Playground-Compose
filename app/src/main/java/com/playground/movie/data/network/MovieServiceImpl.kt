package com.playground.movie.data.network

import com.playground.movie.data.dto.SearchResultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Parameters


/**
 * @Details :MovieServiceImpl
 * @Author Roshan Bhagat
 */
class MovieServiceImpl(private val client: HttpClient) : MovieService {

    override suspend fun getMovieData(parameters: Parameters): SearchResultResponse? {
        return client.get {
            url(MovieService.MOVIE_URL)
        }.body()
    }

}