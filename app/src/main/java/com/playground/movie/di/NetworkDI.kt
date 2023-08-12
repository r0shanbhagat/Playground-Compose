package com.playground.movie.di

import com.playground.movie.BuildConfig
import com.playground.movie.data.network.MovieService
import com.playground.movie.data.network.MovieServiceImpl
import com.playground.movie.utils.NoInternetException
import com.playground.movie.utils.isNetworkConnected
import com.playground.movie.utils.showLog
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
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
private fun provideKtorClient(): HttpClient {
    val ktorClient = HttpClient(Android) {
        enableLogging(this)
        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 60000L
            connectTimeoutMillis = 60000L
            socketTimeoutMillis = 60000L
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                encodeDefaults = false
                ignoreUnknownKeys = true
            })
        }

        install(ResponseObserver) {
            onResponse { response ->
                showLog("Response:", response.toString())
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
    //Adding Interceptor
    ktorClient.addInterceptor()
    return ktorClient
}

private fun HttpClient.addInterceptor() {
    plugin(HttpSend).intercept { request ->
        if (!isNetworkConnected()) {
            throw NoInternetException(
                "", Throwable(NoInternetException::class.java.toString())
            )
        } else {
            execute(request)
        }
    }
}

private fun enableLogging(httpClientConfig: HttpClientConfig<AndroidEngineConfig>) {
    httpClientConfig.install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                showLog("KtorClient:", message)
            }
        }
        level = if (BuildConfig.DEBUG) {
            LogLevel.ALL
        } else {
            LogLevel.NONE
        }
    }
}

/**
 * Provide api service.
 *
 * @param client Client
 * @return [MovieService]
 */
private fun provideApiService(client: HttpClient): MovieService = MovieServiceImpl(client)
