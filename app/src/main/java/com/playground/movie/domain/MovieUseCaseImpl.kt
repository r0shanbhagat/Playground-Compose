package com.playground.movie.domain

import com.playground.movie.contract.MovieRepository
import com.playground.movie.contract.MovieUseCase
import com.playground.movie.data.dto.MovieDetailUI
import com.playground.movie.data.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @Details BlogContentUseCase
 * @Author Roshan Bhagat
 * { @link https://developer.android.com/kotlin/coroutines/coroutines-best-practices}
 * @property repository
 * @constructor Create Movie content use case
 */
class MovieUseCaseImpl(private val repository: MovieRepository) : MovieUseCase {

    override suspend fun getMovieDetailList(params: Unit): Flow<List<MovieDetailUI>> {
        return repository.getMovieList().map { response ->
            val movieDetailList = MovieMapper().mapFromSearchResponse(response)
            movieDetailList.sortedByDescending {
                it.year
            }
        }
    }

}