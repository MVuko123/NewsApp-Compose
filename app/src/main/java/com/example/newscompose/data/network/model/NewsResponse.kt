package com.example.newscompose.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("results")
    val news: List<ApiNews>,
)
