package com.playground.movie.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.playground.movie.contract.MovieUseCase
import com.playground.movie.core.BaseViewModel
import com.playground.movie.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * @Details Movie parse view model : Viewmodel to handle all the business logic
 * @Author Roshan Bhagat
 * StateFlow :https://developer.android.com/topic/architecture/ui-layer#views
 * @property movieUseCase: A bridge object to communicate b/w your repo and data source
 * @constructor
 */
class MovieViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<ViewState> by lazy {
        MutableStateFlow(ViewState.Loading)
    }
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    /**
     * Set state intent
     *
     * @param mainStateEvent
     */
    internal fun setStateIntent(mainStateEvent: MovieStateEvent) {
        when (mainStateEvent) {
            is MovieStateEvent.GetMoviesList -> {
                getMovieList()
            }

            is MovieStateEvent.None -> {
                //TODO will work on New flow
            }
        }
    }

    /*
     * getBlogContent return the movie parsed data using flow that continuously emit the value
     */
    private fun getMovieList() {
        viewModelScope.launch {
            movieUseCase.getMovieDetailList(Unit).catch {
                _uiState.value = ViewState.Failure(it)
            }.collect {
                if (it.isNotEmpty()) {
                    _uiState.emit(ViewState.Success(it))
                } else {
                    _uiState.emit(ViewState.Failure())
                }
            }
        }

    }

}


/**
 * Movie state event.
 *
 * @constructor Create empty constructor for movie state event
 */
sealed class MovieStateEvent {
    /**
     * Get movie list
     *
     * @constructor
     */
    data object GetMoviesList : MovieStateEvent()

    /**
     * None
     *
     * @constructor Create empty None
     */
    data object None : MovieStateEvent()
}