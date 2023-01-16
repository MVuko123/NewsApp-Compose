package com.example.newscompose.data.network

import com.example.newscompose.data.network.model.NewsResponse

interface NewsService {
    suspend fun fetchEUNews(): NewsResponse
    suspend fun fetchUkraineNews(): NewsResponse
    suspend fun fetchTechnologyNews(): NewsResponse
    suspend fun fetchPoliticsNews(): NewsResponse
    suspend fun fetchClimateNews(): NewsResponse
    suspend fun fetchUsPoliticsNews(): NewsResponse
    suspend fun fetchSearchedNews(q: String): NewsResponse
}
