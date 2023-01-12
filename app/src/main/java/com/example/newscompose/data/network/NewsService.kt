package com.example.newscompose.data.network

import com.example.newscompose.data.network.model.ApiNewsDetails
import com.example.newscompose.data.network.model.NewsCreditsResponse
import com.example.newscompose.data.network.model.NewsResponse
import com.example.newscompose.data.network.model.Source

interface NewsService {
    suspend fun fetchUnitedStatesNews(): NewsResponse
    suspend fun fetchUkraineNews(): NewsResponse
    suspend fun fetchTechnologyNews(): NewsResponse
    suspend fun fetchPoliticsNews(): NewsResponse
    suspend fun fetchClimateNews(): NewsResponse
    suspend fun fetchUsPoliticsNews(): NewsResponse
    suspend fun fetchSearchedNews(source: Source?): NewsResponse
    suspend fun fetchNewsDetails(source: Source?): ApiNewsDetails
    suspend fun fetchNewsCredits(source: Source?): NewsCreditsResponse
}
