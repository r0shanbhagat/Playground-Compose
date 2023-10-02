package com.playground.movie.data.network.ssl.pinning

import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object SslSettings {
    fun getKeyStore(): KeyStore {
        val keyStoreFile = FileInputStream("keystore.jks")
        val keyStorePassword = "foobar".toCharArray()
        val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(keyStoreFile, keyStorePassword)
        return keyStore
    }

    fun getTrustManagerFactory(): TrustManagerFactory? {
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(getKeyStore())
        return trustManagerFactory
    }

    fun getSslContextFromKeystore(): SSLContext? {
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, getTrustManagerFactory()?.trustManagers, null)
        return sslContext
    }


    fun getSslContextFromPin(): SSLContext? {
        val trustedPins = mutableListOf("aw1yZI5aicDBPUwJd9u8FD/rUxF10b5s4yWoB5VVHCY=")
        val url = "https://sha256.badssl.com"
        val trustManager = arrayOf(TrustManager(trustedPins, url, 0))
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, trustManager, null)
        return sslContext
    }

    fun getTrustManager(): X509TrustManager {
        return getTrustManagerFactory()?.trustManagers?.first { it is X509TrustManager } as X509TrustManager
    }
}