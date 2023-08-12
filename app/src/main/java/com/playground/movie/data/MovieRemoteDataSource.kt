package com.playground.movie.data

import com.playground.movie.contract.MovieDataSource
import com.playground.movie.data.dto.SearchResultResponse
import com.playground.movie.data.network.MovieService
import io.ktor.http.ParametersBuilder


/**
 * @Details :MovieRemoteDataSource
 * @Author Roshan Bhagat
 *
 * @property apiService
 * @constructor Create Movie parse remote data source
 */
class MovieRemoteDataSource(private val apiService: MovieService) : MovieDataSource {

    override suspend fun getMovieList(): SearchResultResponse? {
        val builder = ParametersBuilder().apply {
            append("s", "Avenger")
            append("apiKey", "Sdsd")
            append("page", "1")
        }.build()
        return apiService.getMovieData(builder)
    }
}
