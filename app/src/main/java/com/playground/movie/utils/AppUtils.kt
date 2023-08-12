package com.playground.movie.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.TextUtils
import android.util.Log
import com.playground.movie.BuildConfig
import com.playground.movie.MovieApp

/**
 * @Details AppUtils: Common Utility Class to handle the utils functions
 * @Author Roshan Bhagat
 */

/**
 * Is network connected check and return the n/w availability of user's device
 *
 * @param context
 * @return boolean true isNetworkConnected else false
 */
fun isNetworkConnected(): Boolean {
    val connectivityManager =
        MovieApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            || capabilities.hasTransport(
        NetworkCapabilities.TRANSPORT_VPN
    ))
}

/**
 * Show log
 *
 * @param tagName
 * @param message
 */
fun showLog(tagName: String?, message: String) {
    if (BuildConfig.DEBUG && !TextUtils.isEmpty(message)) {
        val maxLogSize = message.length + 1
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > message.length)
                message.length
            else end
            Log.v(tagName, message.substring(start, end))
        }
    }
}

/**
 * Log exception
 *
 * @param t
 */
fun logException(t: Throwable?) {
    if (BuildConfig.DEBUG) {
        Log.e("", Log.getStackTraceString(t))
    }
}

/**
 * Is list not empty
 *
 * @param list
 */
fun isListNotEmpty(list: List<Any>?) = !(list?.isEmpty() ?: true)

/**
 * Is valid string
 *
 * @param value
 */
fun String?.isValidString() = !TextUtils.isEmpty(this)