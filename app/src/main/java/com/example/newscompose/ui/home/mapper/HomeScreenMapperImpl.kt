package com.example.newscompose.ui.home.mapper

import com.example.newscompose.R
import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.ui.components.NewsLabelTextViewState
import com.example.newscompose.ui.components.NewsLabelViewState
import com.example.newscompose.ui.home.HomeCategoryViewState
import com.example.newscompose.ui.home.HomeNewsViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeCategoryViewState(
        newsCategories: List<NewsCategory>,
        selectedNewsCategory: NewsCategory,
        news: List<News>,
    ): HomeCategoryViewState =
        HomeCategoryViewState(
            newsCategories.map { newsCategory ->
                NewsLabelViewState(
                    itemId = newsCategory.ordinal,
                    isSelected = newsCategory == selectedNewsCategory,
                    text = NewsLabelTextViewState.NewsStringRes(
                        getStringRes(newsCategory)
                    )
                )
            },
            news.map { news ->
                HomeNewsViewState(
                    url = news.url,
                    source = news.source,
                    headImageUrl = news.headImageUrl,
                    publishedAt = news.date,
                    title = news.headline,
                    isSaved = news.isSaved
                )
            }
        )

    private fun getStringRes(newsCategory: NewsCategory): Int {
        return when (newsCategory) {
            NewsCategory.NEWS_EU -> R.string.europe
            NewsCategory.NEWS_CLIMATE -> R.string.climate
            NewsCategory.NEWS_POLITICS -> R.string.politics
            NewsCategory.NEWS_UKRAINE -> R.string.ukraine
            NewsCategory.NEWS_TECHNOLOGY -> R.string.technology
            NewsCategory.NEWS_US_POLITICS -> R.string.us_politics
        }
    }
}
