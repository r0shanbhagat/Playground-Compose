package com.playground.movie.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigDataModel(
    @SerialName("PcsConfig")
    val pcsConfig: PcsConfig?
) {
    @Serializable
    data class PcsConfig(
        @SerialName("bundleHeaders")
        val bundleHeaders: List<BundleHeader>,
        @SerialName("metaConfig")
        val metaConfig: MetaConfig,
        @SerialName("urlbase")
        val urlbase: String
    ) {
        @Serializable
        data class BundleHeader(
            @SerialName("headerName")
            val headerName: String,
            @SerialName("headerValue")
            val headerValue: String
        )

        @Serializable
        data class MetaConfig(
            @SerialName("enabled")
            val enabled: Boolean,
            @SerialName("index")
            val index: Int,
            @SerialName("pinningCerts")
            val pinningCerts: List<String>
        )
    }
}