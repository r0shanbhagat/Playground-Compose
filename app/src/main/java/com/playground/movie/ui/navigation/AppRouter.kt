package com.playground.movie.ui.navigation

sealed class AppRouter(path: String) : AppRouterArgs(path) {
    data object LoginScreen : AppRouter("LoginScreen")
    data object SignUpScreen : AppRouter("SignUpScreen")
    data object MovieListScreen : AppRouter("MovieListScreen")
    data object TermsAndConditionsScreen : AppRouter("TermsAndConditionsScreen")
}

sealed class AppRouterArgs(val path: String) {
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}
