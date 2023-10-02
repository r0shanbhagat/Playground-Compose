package com.playground.movie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.playground.movie.utils.FirebaseConfig
import com.playground.movie.ui.navigation.NavGraph
import com.playground.movie.ui.theme.MovieComposeTheme
import org.koin.android.ext.android.inject


class MovieActivity : ComponentActivity() {

    private val remoteConfig: FirebaseConfig by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        remoteConfig.fetchFirebaseConfig()
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
            NavGraph(navController)
        }
    }

}