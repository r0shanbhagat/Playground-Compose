package com.playground.movie.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.playground.movie.contract.MovieRepository
import com.playground.movie.core.BaseViewModel
import com.playground.movie.data.dto.MovieModel
import com.playground.movie.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Details Movie parse view model : Viewmodel to handle all the business logic
 * @Author Roshan Bhagat
 * StateFlow :https://developer.android.com/topic/architecture/ui-layer#views
 * @constructor
 */
class MovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    private val _uiState: MutableStateFlow<ViewState> by lazy {
        MutableStateFlow(ViewState.Loading)
    }

    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    private var moviesList: ArrayList<MovieModel> = ArrayList()

    init {
        setStateIntent(MovieStateEvent.GetMoviesList)
    }

    /**
     * Set state intent
     *
     * @param mainStateEvent
     */
    private fun setStateIntent(mainStateEvent: MovieStateEvent) {
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
            moviesList.clear()
            movieRepository
                .getMovieList()
                //TODO handled the Error Sceanrio
//                .catch { exception ->
//                    _uiState.value = ViewState.Failure(exception)
//                }
                .collect { it ->
                    moviesList.addAll(it)
                    moviesList.sortByDescending {
                        it.year
                    }

                    _uiState.emit(ViewState.Success(moviesList))
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