package com.playground.movie.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.playground.movie.data.dto.MovieModel
import com.playground.movie.ui.compose.ShowMovieItem
import com.playground.movie.ui.theme.MovieComposeTheme
import com.playground.movie.ui.viewmodel.MovieViewModel
import com.playground.movie.utils.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieActivity : ComponentActivity() {

    private val viewModel by viewModel<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState: ViewState by viewModel.uiState.collectAsStateWithLifecycle()
            MovieComposeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (uiState) {
                        is ViewState.Loading -> CircularProgressIndicator()
                        is ViewState.Success -> { // success
                            ShowMovieItem((uiState as ViewState.Success).data as List<MovieModel>)
                        }

                        is ViewState.Failure -> {
                            (uiState as ViewState.Failure).throwable.message?.let {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    }

}