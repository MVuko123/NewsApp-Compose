package com.example.newscompose.model

import com.example.newscompose.data.network.model.Source

data class News(
    val source: Source? = null,
    val headImageUrl: String,
    val headline: String,
    val date: String,
    val isSaved: Boolean
)
