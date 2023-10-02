package com.playground.movie.data.network

import com.playground.movie.BuildConfig
import com.playground.movie.data.dto.SearchResultResponse
import io.ktor.http.Parameters


/**
 * @Details ApiService
 * @Author Roshan Bhagat
 * @constructor Create Api service
 */
interface MovieService {

    companion object {
        const val MOVIE_URL =
            "https://sha256.badssl.com"
    }

    /**
     * Performs a GET call to obtain a paginated list of movies
     * @param parameters API Key
     *  searchTitle feature source the movie should come from
     *  pageIndex Page number of the data where the movie should come from
     *  Response instance of [SearchResultResponse] type
     */
    suspend fun getMovieData(parameters: Parameters): SearchResultResponse?


}