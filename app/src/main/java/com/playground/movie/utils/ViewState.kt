package com.playground.movie.utils

/**
 * @Details DataState: It holds the state of your app service data.This way we can write
 * the business logic in more clear way
 * @Author Roshan Bhagat
 */
sealed interface ViewState {

    /**
     * Success
     *
     * @property data
     * @constructor Create Success
     */
    data class Success(val data: Any?) : ViewState

    /**
     * Error
     *
     * @property throwable
     * @constructor Create  Error
     */
    data class Failure(val throwable: Throwable? = null) : ViewState

    /**
     * Loading
     *
     * @constructor Create empty Loading
     */
    data object Loading : ViewState
}