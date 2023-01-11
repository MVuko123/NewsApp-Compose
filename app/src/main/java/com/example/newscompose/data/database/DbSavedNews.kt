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
    val headImageUrl: String,
    @Embedded
    val newsSource: Source?,
)
