package com.playground.movie.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playground.movie.ui.screens.LoginScreen
import com.playground.movie.ui.screens.MovieListScreen
import com.playground.movie.ui.screens.SignUpScreen
import com.playground.movie.ui.screens.TermsAndConditionsScreen
import com.playground.movie.utils.navigateWithArgs

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.SignUpScreen.route
    ) {
        addSignupScreen(navController, this)
        addLoginScreen(navController, this)
        addMovieListScreen(this)
        addTermsAndConditionsScreen(this)
    }
}

private fun addSignupScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.SignUpScreen.route) {
        SignUpScreen(navigateToTermsPage = {
            navController.navigate(NavRoute.TermsAndConditionsScreen.route)
        }, navigateToLogin = { emailId, password ->
            val args = Bundle().apply {
                putString("emailId", emailId)
                putString("password", password)
            }
            navController.navigateWithArgs(
                route = NavRoute.LoginScreen.route, args = args
            )
        })
    }
}

private fun addLoginScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.LoginScreen.route
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        val emailId: String = args?.getString("emailId").toString()
        val password: String = args?.getString("password").toString()
        LoginScreen(email = emailId, pwd = password, loginButtonClick = {
            navController.navigate(NavRoute.MovieListScreen.route)
        }, registerButtonClick = {
            popUpToSignup(navController)
        })
    }
}

private fun addTermsAndConditionsScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.TermsAndConditionsScreen.route) {
        TermsAndConditionsScreen()
    }
}

private fun addMovieListScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.MovieListScreen.route) {
        MovieListScreen()
    }
}

private fun popUpToSignup(navController: NavHostController) {
    navController.popBackStack(NavRoute.SignUpScreen.route, inclusive = false)
}
