package com.playground.movie.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.playground.movie.R
import com.playground.movie.data.dto.MovieDetailUI
import com.playground.movie.ui.viewmodel.MovieStateEvent
import com.playground.movie.ui.viewmodel.MovieViewModel
import com.playground.movie.utils.ViewState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.setStateIntent(MovieStateEvent.GetMoviesList)
    }

    val uiState: ViewState by viewModel.uiState.collectAsStateWithLifecycle()
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
                ShowMovieList((uiState as ViewState.Success).data as List<MovieDetailUI>)
            }

            is ViewState.Failure -> {
                (uiState as ViewState.Failure).throwable?.message?.let {
                    Toast.makeText(
                        LocalContext.current, "Something wents wrong!!", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

@Composable
fun ShowMovieList(moviesList: List<MovieDetailUI>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(moviesList) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.None,
                        model = it.image,
                        contentDescription = null // decorative image
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = it.title, fontSize = 18.sp
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = it.title, fontSize = 12.sp
                    )
                }
            }
        }
    }
}