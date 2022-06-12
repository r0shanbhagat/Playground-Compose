package com.playground.movie.di

import com.playground.movie.BuildConfig
import com.playground.movie.data.api.MovieService
import com.playground.movie.data.api.MovieServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

/**
 * @Details NetworkModule
 * @Author Roshan Bhagat
 * @constructor Create Network module
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = if (BuildConfig.DEBUG) {
                LogLevel.ALL
            } else {
                LogLevel.NONE
            }
        }

        val json = kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }

    @Provides
    @Singleton
    fun provideApi(client: HttpClient): MovieService = MovieServiceImpl(client)

}