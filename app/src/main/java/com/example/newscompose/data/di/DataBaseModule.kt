package com.example.newscompose.data.di

import androidx.room.Room
import com.example.newscompose.data.database.NewsAppDatabase
import com.example.newscompose.data.database.SavedNewsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app.database.db"

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NewsAppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }
    fun savedNewsDao(db: NewsAppDatabase): SavedNewsDao = db.savedNewsDao()
    single {
        savedNewsDao(get())
    }
}
