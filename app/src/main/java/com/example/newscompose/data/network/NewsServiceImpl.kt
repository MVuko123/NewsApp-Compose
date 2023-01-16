package com.example.newscompose.data.network

import com.example.newscompose.data.network.model.ApiNewsDetails
import com.example.newscompose.data.network.model.NewsCreditsResponse
import com.example.newscompose.data.network.model.NewsResponse
import com.example.newscompose.data.network.model.Source
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=595ad80691dc4b4d87375de0804b5e98

private const val BASE_URL = "https://newsapi.org/v2"
private const val API_KEY = "595ad80691dc4b4d87375de0804b5e98"

class NewsServiceImpl(private val client: HttpClient) : NewsService{
    override suspend fun fetchEUNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=european+union&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchUkraineNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=ukraine+war&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchTechnologyNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=technology&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchPoliticsNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=politics&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchClimateNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=climate+change&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchUsPoliticsNews(): NewsResponse =
        client.get("$BASE_URL/everything?q=us+politics&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchSearchedNews(q: String): NewsResponse =
        client.get("$BASE_URL/everything?search?q=$q&language=en&sortBy=publishedAt&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchNewsDetails(url: String): ApiNewsDetails =
        client.get("$BASE_URL/url=$url&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchNewsCredits(url: String): NewsCreditsResponse =
        client.get("$BASE_URL/url=$url/credits&apiKey=$API_KEY&image=true").body()
}
