package com.example.newscompose.ui.home.mapper

import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.ui.home.HomeCategoryViewState

interface HomeScreenMapper {
    fun toHomeCategoryViewState(
        newsCategories: List<NewsCategory>,
        selectedNewsCategory: NewsCategory,
        news: List<News>
    ):HomeCategoryViewState
}
