package com.example.newscompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbSavedNews::class], version = 1, exportSchema = false)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun savedNewsDao(): SavedNewsDao
}
