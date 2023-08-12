package com.playground.movie

import com.playground.movie.di.appModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import kotlin.test.Test

/**
 * Check modules test.
 *
 * Refer :https://insert-koin.io/docs/quickstart/android-viewmodel#verifying-your-app
 *
 * @constructor Create empty constructor for check modules test
 */
class CheckModulesTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        appModule.verify(
            extraTypes = listOf(
                io.ktor.client.engine.HttpClientEngine::class,
                io.ktor.client.HttpClientConfig::class
            )
        )
    }
}