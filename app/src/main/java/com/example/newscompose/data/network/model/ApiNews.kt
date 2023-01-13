package com.example.newscompose.data.network.model

import com.example.newscompose.model.News
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ApiNews(
  @SerialName("source")
  val source: Source?,
  @SerialName("urlToImage")
  val headImageUrl: String?,
  @SerialName("title")
  val title: String,
  @SerialName("publishedAt")
  val date: String
){
  fun toNews(isSaved: Boolean) = News(
    source = source,
    headImageUrl = headImageUrl,
    headline = title,
    date = date,
    isSaved = isSaved
  )
}

@Serializable
data class Source(
  @SerialName("id")
  val id: String? = null,
  @SerialName("name")
  val name: String? = null
)
