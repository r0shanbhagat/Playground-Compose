package com.playground.movie.core

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

/**
 * Base VM for corresponding sub View Models to extend and take advantage of DI.
 */
abstract class BaseViewModel : ViewModel(), KoinComponent
