package com.playground.movie.di

import com.playground.movie.contract.MovieUseCase
import com.playground.movie.domain.MovieUseCaseImpl
import org.koin.dsl.module

/**
 * All use case dependency can be prepared here.
 * For demonstration created Landing Use case alone.
 */
val useCaseKoinModule = module {
    factory<MovieUseCase> {
        // If repository is required to be passed over, while creating dependency itself Koin will support
        MovieUseCaseImpl(get())
    }
}