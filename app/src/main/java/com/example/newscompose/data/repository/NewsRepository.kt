package com.example.newscompose.data.repository

import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun news(newsCategory: NewsCategory): Flow<List<News>>
    fun savedNews(): Flow<List<News>>
    fun searchNews(topic: String): Flow<List<News>>
    suspend fun addNewsToSaved(url: String)
    suspend fun removeNewsFromSaved(url: String)
    suspend fun toggleSaved(url: String)
}
