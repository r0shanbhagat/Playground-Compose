package com.playground.movie.contract

import com.playground.movie.data.dto.SearchResultResponse
import kotlinx.coroutines.flow.Flow


/**
 * @Details :IRepository
 * @Author Roshan Bhagat
 *@Link https://developer.android.com/topic/architecture/data-layer
 * https://medium.com/@BerkOzyurt/android-clean-architecture-mvvm-usecase-ae1647f0aea3
 *
 * @param
 * @constructor Create Repository
 */
interface MovieRepository {

    /**
     * Get getMovieList result data.
     *
     * @return
     */
    suspend fun getMovieList(): Flow<SearchResultResponse?>

}