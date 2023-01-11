package com.example.newscompose.data.network.model

import com.example.newscompose.model.News
import com.example.newscompose.model.NewsDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ApiNewsDetails(
    @SerialName("source")
    val source: Source,
    @SerialName("urlToImage")
    val headImageUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("author")
    val author: String,
    @SerialName("content")
    val content: String
){
    fun toNewsDetails(isSaved: Boolean) = NewsDetails(
        news = News(
            source = source,
            headImageUrl = headImageUrl,
            headline = title,
            date = date,
            isSaved = isSaved
        ),
        author = author,
        text = content
    )
}
