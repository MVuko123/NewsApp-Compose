package com.example.newscompose.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedNews")
data class DbSavedNews(
    @PrimaryKey
    val url: String,
    val headImageUrl: String?,
    val headline: String,
    val date: String,
)
