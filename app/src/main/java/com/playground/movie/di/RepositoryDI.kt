package com.playground.movie.di

import com.playground.movie.contract.MovieRepository
import com.playground.movie.data.MoviesRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @Details :Repository DI
 * @Author Roshan Bhagat
 */

val movieRepositoryKoinModule = module {
    single<MovieRepository> {
        MoviesRepositoryImpl(
            apiService = get(), ioDispatcher = get(named(DispatcherDI.DISPATCHER_IO))
        )
    }
}