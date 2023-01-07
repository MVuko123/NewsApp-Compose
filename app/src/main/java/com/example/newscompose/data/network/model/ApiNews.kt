package com.example.newscompose.data.network.model

import com.example.newscompose.model.News
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ApiNews(
  @SerialName("id")
  val id: Int,
  @SerialName("urlToImage")
  val headImageUrl: String,
  @SerialName("title")
  val title: String,
  @SerialName("publishedAt")
  val date: Date
){
  fun toNews() = News(
    id = id,
    headImageUrl = headImageUrl,
    headline = title,
    date = date
  )
}
