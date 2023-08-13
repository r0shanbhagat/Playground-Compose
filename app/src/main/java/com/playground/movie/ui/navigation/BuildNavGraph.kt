package com.playground.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playground.movie.ui.screens.LoginScreen
import com.playground.movie.ui.screens.MovieListScreen
import com.playground.movie.ui.screens.SignUpScreen
import com.playground.movie.ui.screens.TermsAndConditionsScreen

@Composable
fun BuildNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = AppRouter.LoginScreen.path
    ) {
        addLoginScreen(navController, this)
        addSignupScreen(navController, this)
        addTermsAndConditionsScreen(this)
        addMovieListScreen(this)
    }
}

private fun addLoginScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AppRouter.LoginScreen.path) {
        LoginScreen(
            navigateToSignUp = {
                navController.navigate(AppRouter.SignUpScreen.path)
            },
        )
    }
}

private fun addSignupScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AppRouter.SignUpScreen.path) {
        SignUpScreen(navController)
    }
}

private fun addTermsAndConditionsScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AppRouter.TermsAndConditionsScreen.path) {
        TermsAndConditionsScreen()
    }
}

private fun addMovieListScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AppRouter.MovieListScreen.path) {
        val id: String? = it.arguments?.getString("userId")
        MovieListScreen(id.toString())
    }
}