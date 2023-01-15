package com.example.newscompose.ui.search

import com.example.newscompose.data.network.model.Source

data class SearchViewState(
    val source: Source?,
    val headImageUrl: String?,
    val publishedAt: String,
    val title: String,
    val isSaved: Boolean
)

data class SearchCategoryViewState(
    val search: List<SearchViewState> = listOf()
)
