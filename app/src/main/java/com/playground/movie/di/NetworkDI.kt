package com.playground.movie.di

import com.playground.movie.BuildConfig
import com.playground.movie.data.api.MovieService
import com.playground.movie.data.api.MovieServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/**
 * @Details NetworkModule for Project
 * @Author Roshan Bhagat
 * @constructor Create Network module
 */

val networkKoinModule = module {
    single { provideKtorClient() }
    single { provideApiService(get()) }
}

/**
 * Provide ktor client.
 * @return : HttpClient
 */
private fun provideKtorClient() = HttpClient(Android) {
    install(Logging) {
        level = if (BuildConfig.DEBUG) {
            LogLevel.ALL
        } else {
            LogLevel.NONE
        }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            encodeDefaults = false
            ignoreUnknownKeys = true
        })
    }
}

/**
 * Provide api service.
 *
 * @param client Client
 * @return [MovieService]
 */
private fun provideApiService(client: HttpClient): MovieService = MovieServiceImpl(client)
