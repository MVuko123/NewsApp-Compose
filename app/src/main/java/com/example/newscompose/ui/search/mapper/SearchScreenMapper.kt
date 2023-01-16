package com.example.newscompose.ui.search.mapper

import com.example.newscompose.model.News
import com.example.newscompose.ui.search.SearchCategoryViewState

interface SearchScreenMapper {
    fun toSearchCategoryViewState(
        news: List<News>,
    ): SearchCategoryViewState
}
