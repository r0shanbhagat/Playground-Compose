package com.playground.movie.contract

import com.playground.movie.data.dto.MovieDetailUI
import kotlinx.coroutines.flow.Flow

/**
 * MovieUseCase
 *
 * @param
 * @constructor Create empty I use case
 */
interface MovieUseCase {

    /**
     * Get movie content
     *
     * @return
     */
    suspend fun getMovieDetailList(params: Unit): Flow<List<MovieDetailUI>>
}