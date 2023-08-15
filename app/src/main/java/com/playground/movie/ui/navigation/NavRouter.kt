package com.playground.movie.ui.navigation

sealed class NavRoute(val route: String) {
    data object SignUpScreen : NavRoute("SignUpScreen")
    data object LoginScreen : NavRoute("LoginScreen")
    data object MovieListScreen : NavRoute("MovieListScreen")
    data object TermsAndConditionsScreen : NavRoute("TermsAndConditionsScreen")
}