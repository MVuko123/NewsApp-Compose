package com.example.newscompose.data.network

import com.example.newscompose.data.network.model.NewsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

const val BASE_URL = "https://newsapi.org/v2"
private const val API_KEY = "f11c78d3dfd14ec18b8bf0e5df495730"

class NewsServiceImpl(private val client: HttpClient) : NewsService {
    override suspend fun fetchEUNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=european+union&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchUkraineNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=ukraine+war&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchTechnologyNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=technology&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchPoliticsNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=politics&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchClimateNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=climate+change&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchUsPoliticsNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=us+politics&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()

    override suspend fun fetchSearchedNews(q: String): NewsResponse {
        val query = q.ifBlank { "all" }
        return client.get("$BASE_URL/everything?q=$query&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true")
            .body()
    }
}
