package com.playground.movie.contract

import com.playground.movie.data.dto.SearchResultResponse


/**
 * @Details :MovieDataSource is an Data Source interface to fetch the movie data
 * @Author Roshan Bhagat
 * @param
 * @constructor Create Movie data source
 */
interface MovieDataSource {

    /**
     * Get MovieList
     *
     * @return
     */
    suspend fun getMovieList(): SearchResultResponse?

}