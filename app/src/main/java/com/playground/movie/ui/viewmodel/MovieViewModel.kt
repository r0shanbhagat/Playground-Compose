package com.playground.movie.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.playground.movie.contract.Repository
import com.playground.movie.core.BaseViewModel
import com.playground.movie.data.dto.MovieModel
import com.playground.movie.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Details Movie parse view model : Viewmodel to handle all the business logic
 * @Author Roshan Bhagat
 * StateFlow :https://developer.android.com/topic/architecture/ui-layer#views
 * @constructor
 */
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _uiState: MutableState<ViewState> by lazy {
        mutableStateOf(ViewState())
    }

    val uiState: State<ViewState> = _uiState

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
            repository
                .getMovieList()
                .onStart {
                    _uiState.value = ViewState.Loading
                }
                .catch { exception ->
                    _uiState.value = ViewState.Failure(exception)
                }
                .collect { it ->
                    moviesList.addAll(it)
                    moviesList.sortByDescending {
                        it.year
                    }

                    _uiState.value = ViewState.Success(moviesList)
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
    object GetMoviesList : MovieStateEvent()

    /**
     * None
     *
     * @constructor Create empty None
     */
    object None : MovieStateEvent()
}