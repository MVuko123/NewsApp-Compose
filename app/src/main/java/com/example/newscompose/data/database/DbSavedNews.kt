package com.example.newscompose.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newscompose.data.network.model.Source

@Entity(tableName = "savedNews")
data class DbSavedNews(
    @PrimaryKey
    val url: String,
    val headImageUrl: String?,
    val headline: String,
    val date: String,
    //val isSaved: Boolean,
    //val author: String,
    //val content: String
)
