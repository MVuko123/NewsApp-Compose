package com.example.newscompose.data.repository

import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.model.NewsDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface NewsRepository {
    fun news(newsCategory: NewsCategory): Flow<List<News>>
    fun newsDetails(url: String): Flow<NewsDetails>
    fun savedNews(): Flow<List<News>>
    fun searchNews(topic: String): Flow<List<News>>
    suspend fun addNewsToSaved(url: String)
    suspend fun removeNewsFromSaved(url: String)
    suspend fun toggleSaved(url: String)
}
