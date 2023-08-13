package com.playground.movie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.playground.movie.ui.navigation.BuildNavGraph
import com.playground.movie.ui.theme.MovieComposeTheme

class MovieActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MovieApp()
    }

    @Composable
    private fun MovieApp() {
        MovieComposeTheme {
            val navController = rememberNavController()
            BuildNavGraph(navController)
        }
    }

}