package com.playground.movie.di

import com.playground.movie.ui.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * @Details View Model Dependency Injection stored herel
 * @Author Roshan Bhagat
 * @constructor Create Repository module
 */
val viewmodelKoinModule = module {
    viewModel {
        MovieViewModel(get())
    }
}

