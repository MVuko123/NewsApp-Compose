package com.example.newscompose.ui.home

import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.ui.components.NewsLabelViewState
import java.util.Date

data class HomeNewsViewState(
    val source: Source?,
    val headImageUrl: String?,
    val date: String,
    val headline: String,
    val isSaved: Boolean
)

data class HomeCategoryViewState(
    val newsCategory: List<NewsLabelViewState> = listOf(),
    val news: List<HomeNewsViewState> = listOf(),
)
