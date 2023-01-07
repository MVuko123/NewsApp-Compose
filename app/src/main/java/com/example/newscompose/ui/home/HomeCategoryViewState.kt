package com.example.newscompose.ui.home

import com.example.newscompose.model.NewsCategory
import com.example.newscompose.ui.components.NewsLabelViewState
import java.util.Date

data class HomeNewsViewState(
    val id: Int,
    val headImageUrl: String?,
    val date: Date,
    val headline: String
)

data class HomeCategoryViewState(
    val newsCategory: List<NewsLabelViewState> = listOf(),
    val news: List<HomeNewsViewState> = listOf(),
)
