package com.example.newscompose.ui.search.mapper

import com.example.newscompose.model.News
import com.example.newscompose.ui.search.SearchCategoryViewState
import com.example.newscompose.ui.search.SearchViewState

class SearchScreenMapperImpl(): SearchScreenMapper {
    override fun toSearchCategoryViewState(news: List<News>): SearchCategoryViewState =
        SearchCategoryViewState(
            news.map { news ->
                SearchViewState(
                    url = news.url,
                    source = news.source,
                    headImageUrl = news.headImageUrl,
                    publishedAt = news.date,
                    title = news.headline,
                    isSaved = news.isSaved
                )
            }
        )
}
