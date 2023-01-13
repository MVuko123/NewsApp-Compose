package com.example.newscompose.ui.newsDetails

import com.example.newscompose.data.network.model.Source

data class NewsDetailsViewState(
    val source: Source?,
    val headImageUrl : String?,
    val title: String,
    val date: String,
    val author: String,
    val content: String,
    val isSaved: Boolean
)
