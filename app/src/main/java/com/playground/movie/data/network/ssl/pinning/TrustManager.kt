package com.playground.movie.data.network.ssl.pinning

import android.net.http.X509TrustManagerExtensions
import android.util.Base64
import android.util.Log
import com.playground.movie.BuildConfig
import com.playground.movie.utils.isValidString
import io.ktor.http.Url
import java.security.KeyStore
import java.security.MessageDigest
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.Arrays
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class TrustManager(
    private val trustedPins: MutableList<String>,
    private val baseUrl: String,
    private val index: Int
) : X509TrustManager {

    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
    override fun checkServerTrusted(chain: Array<X509Certificate>?, authType: String?) {
        var pfs = ""
        if (chain.isNullOrEmpty() || !authType.isValidString()) {
            Log.e(TAG, "Exception in certificate Pinning")
            return
        }

        val tmf: TrustManagerFactory = TrustManagerFactory.getInstance("X509")
        var serverCertificate: X509Certificate? = null
        var trustManagerExt: X509TrustManagerExtensions? = null
        try {
            tmf.init(null as KeyStore?)
            var x509TrustManager: X509TrustManager? = null
            for (trustManager in tmf.trustManagers) {
                if (trustManager is X509TrustManager) {
                    x509TrustManager = trustManager
                    break
                }
            }
            trustManagerExt = X509TrustManagerExtensions(x509TrustManager)
        } catch (cause: Exception) {
            Log.e("TrustManager", "Exception in certificate Pinning" + cause.localizedMessage)
        }

        try {
            for (trustManager in tmf.trustManagers) {
                (trustManager as X509TrustManager).checkServerTrusted(chain, authType)
            }
            val untrustedCerts =
                Arrays.copyOf(chain, chain.size, Array<X509Certificate>::class.java)

            serverCertificate =
                trustManagerExt?.checkServerTrusted(untrustedCerts, "RSA", Url(baseUrl).host)
                    ?.get(index)
        } catch (cause: CertificateException) {
            Log.e("TrustManager", "Exception in certificate Pinning" + cause.localizedMessage)
        } catch (cause: IndexOutOfBoundsException) {
            Log.e("TrustManager", "Exception in certificate Pinning" + cause.localizedMessage)
        }
        try {
            val publicKey = serverCertificate?.publicKey?.encoded
            val md = MessageDigest.getInstance("SHA-256")
            publicKey?.size?.let { md.update(publicKey, 0, it) }
            pfs = Base64.encodeToString(md.digest(), Base64.NO_WRAP)
        } catch (cause: Exception) {
        }
        if (trustedPins.size > 0) {
            var expected = false
            for (trustedPin in trustedPins) {
                if (trustedPin.equals(pfs, ignoreCase = true)) {
                    if (BuildConfig.DEBUG) Log.d(TAG, "Pinning successful for " + Url(baseUrl).host)
                    expected = true
                }
            }
            if (!expected) {
                val exceptionMsg =
                    "checkServerTrusted: Expected public key: " + trustedPins[index] + ", got public key:" + pfs
                Log.d(TAG, "Pinning failed Msg :: $exceptionMsg")
                Log.e(TAG, "Exception in certificate Pinning$exceptionMsg")
            }
        }
    }

    override fun getAcceptedIssuers(): Array<X509Certificate>? {
        return null
    }

    companion object {
        private val TAG = TrustManager::class.java.simpleName
    }
}