package com.example.newscompose.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsCreditsResponse (
    @SerialName("id")
    val id: Long?
)

