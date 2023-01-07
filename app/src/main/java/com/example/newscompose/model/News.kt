package com.example.newscompose.model

import java.util.Date

data class News(
    val id: Int,
    val headImageUrl: String,
    val headline: String,
    val date: Date,
)
