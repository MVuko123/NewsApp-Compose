package com.example.newscompose.ui.home

import com.example.newscompose.data.network.model.Source
import com.example.newscompose.ui.components.NewsLabelViewState

data class HomeNewsViewState(
    val source: Source?,
    val headImageUrl: String?,
    val publishedAt: String,
    val title: String,
    val isSaved: Boolean
)

data class HomeCategoryViewState(
    val newsCategory: List<NewsLabelViewState> = listOf(),
    val news: List<HomeNewsViewState> = listOf(),
)
