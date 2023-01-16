package com.example.newscompose.data.repository

import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.model.NewsDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface NewsRepository {
    fun news(newsCategory: NewsCategory): Flow<List<News>>
    fun newsDetails(id: Long?): Flow<NewsDetails>
    fun savedNews(): Flow<List<News>>
    fun searchNews(topic: String): Flow<List<News>>
    suspend fun addNewsToSaved(id: Long?)
    suspend fun removeNewsFromSaved(id: Long?)
    suspend fun toggleSaved(id: Long?)
}
