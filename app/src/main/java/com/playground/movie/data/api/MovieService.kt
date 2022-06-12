package com.playground.movie.data.api

import com.playground.movie.BuildConfig
import com.playground.movie.data.dto.SearchResults
import io.ktor.http.*


/**
 * @Details ApiService
 * @Author Roshan Bhagat
 * @constructor Create Api service
 */
interface MovieService {

    companion object {
        const val MOVIE_URL =
            "${BuildConfig.BASE_URL}?type=movie&s=Avenger&apiKey=${BuildConfig.API_KEY}&page=1"
    }

    /**
     * Performs a GET call to obtain a paginated list of movies
     * @param parameters API Key
     *  searchTitle feature source the movie should come from
     *  pageIndex Page number of the data where the movie should come from
     *  Response instance of [SearchResults] type
     */
    suspend fun getMovieData(parameters: Parameters): SearchResults


}