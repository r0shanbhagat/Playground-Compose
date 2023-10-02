package com.playground.movie.utils

import com.playground.movie.data.dto.ConfigDataModel

/**
 * @Details :AppSession
 * @Author Roshan Bhagat
 */
class AppSession {
    var configData: ConfigDataModel? = null

    internal fun clear() {
        configData = null
    }
}