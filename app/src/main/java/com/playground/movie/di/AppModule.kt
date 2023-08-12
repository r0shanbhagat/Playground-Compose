package com.playground.movie.di

/**
 * @Details :AppModule
 * @Author Roshan Bhagat
 */
import org.koin.dsl.module

// Main Koin Module
val appModule = module {
    includes(
        viewmodelKoinModule,
        useCaseKoinModule,
        movieRepositoryKoinModule,
        movieDsKoinModule,
        networkKoinModule,
        dispatcherKoinModule,
    )
}