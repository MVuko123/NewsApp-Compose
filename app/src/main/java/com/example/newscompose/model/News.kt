package com.example.newscompose.model

import androidx.room.PrimaryKey
import com.example.newscompose.data.network.model.Source

data class News(
    @PrimaryKey
    val url: String,
    val source: Source?,
    val headImageUrl: String?,
    val headline: String,
    val date: String,
    val isSaved: Boolean
)
