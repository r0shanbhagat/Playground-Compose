package com.playground.movie.di

/**
 * @Details :AppModule
 * @Author Roshan Bhagat
 */
import com.playground.movie.utils.FirebaseConfig
import com.playground.movie.utils.AppSession
import org.koin.dsl.module

// Main Koin Module
val appModule = module {

    single { AppSession() }
    single { FirebaseConfig() }
    includes(
        viewmodelKoinModule,
        useCaseKoinModule,
        movieRepositoryKoinModule,
        movieDsKoinModule,
        networkKoinModule,
        dispatcherKoinModule,
    )
}