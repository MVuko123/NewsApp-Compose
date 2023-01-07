package com.example.newscompose.data.repository

import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.model.NewsDetails
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun news(newsCategory: NewsCategory): Flow<List<News>>
    fun newsDetails(newsId: Int): Flow<NewsDetails>

}
