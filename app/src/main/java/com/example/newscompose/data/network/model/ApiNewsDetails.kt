package com.example.newscompose.data.network.model

import com.example.newscompose.model.News
import com.example.newscompose.model.NewsDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ApiNewsDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("urlToImage")
    val headImageUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("publishedAt")
    val date: Date,
    @SerialName("author")
    val author: String,
    @SerialName("content")
    val content: String
){
    fun toNewsDetails() = NewsDetails(
        news = News(
            id = id,
            headImageUrl = headImageUrl,
            headline = title,
            date = date
        ),
        author = author,
        text = content
    )
}
