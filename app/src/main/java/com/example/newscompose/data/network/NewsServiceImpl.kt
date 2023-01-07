package com.example.newscompose.data.network

import com.example.newscompose.data.network.NewsService
import com.example.newscompose.data.network.model.NewsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=595ad80691dc4b4d87375de0804b5e98

private const val BASE_URL = "https://newsapi.org/v2/top-headlines"
private const val API_KEY = "595ad80691dc4b4d87375de0804b5e98"

class NewsServiceImpl(private val client: HttpClient) : NewsService{
    override suspend fun fetchCroatiaNews(): NewsResponse =
        client.get("$BASE_URL?country=hr&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchUkraineNews(): NewsResponse =
        client.get("$BASE_URL?country=ua&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchTechnologyNews(): NewsResponse =
        client.get("$BASE_URL?category=technology&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchPoliticsNews(): NewsResponse =
        client.get("$BASE_URL?category=politics&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchClimateNews(): NewsResponse =
        client.get("$BASE_URL?category=climate&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchUsPoliticsNews(): NewsResponse =
        client.get("$BASE_URL?country=us&category=politics&apiKey=$API_KEY&image=true").body()

    override suspend fun fetchSearchedNews(country: Int): NewsResponse =
        client.get("$BASE_URL?country=$country&apiKey=$API_KEY&image=true").body()

}
