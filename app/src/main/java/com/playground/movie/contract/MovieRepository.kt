package com.playground.movie.contract

import com.playground.movie.data.api.MovieService
import com.playground.movie.data.dto.MovieModel
import kotlinx.coroutines.flow.Flow


/**
 * @Details :IRepository
 * @Author Roshan Bhagat
 *@Link https://developer.android.com/topic/architecture/data-layer
 *
 * @param
 * @constructor Create Repository
 */
interface MovieRepository {

    val apiService: MovieService

    /**
     * Get getMovieList result data.
     *
     * @return
     */
    suspend fun getMovieList(): Flow<List<MovieModel>>

}