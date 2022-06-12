package com.playground.movie.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.playground.movie.R
import com.playground.movie.data.dto.MovieModel
import com.playground.movie.ui.theme.MovieComposeTheme
import com.playground.movie.ui.viewmodel.MovieViewModel
import com.playground.movie.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewmodel = hiltViewModel<MovieViewModel>()
            val state = viewmodel.uiState.value

            MovieComposeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (state) {
                        is ViewState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is ViewState.Success -> { // success
                            val moviesList: List<MovieModel> = state.data as List<MovieModel>
                            showMovieItem(moviesList)
                        }
                        is ViewState.Failure -> {
                            state.throwable.message?.let {
                                Text(text = it)
                            }
                        }

                    }
                }
            }
        }
    }


    @Composable
    private fun showMovieItem(moviesList: List<MovieModel>) {
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
                        //set the image url
                        val painter = rememberImagePainter(
                            data = it.image,
                            builder = {
                                error(R.drawable.ic_launcher_background)
                            }
                        )

                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Coil Image",
                            painter = painter
                        )
                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                        )
                        Text(
                            text = it.title,
                            fontSize = 18.sp
                        )
                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                        )
                        Text(
                            text = it.title,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}